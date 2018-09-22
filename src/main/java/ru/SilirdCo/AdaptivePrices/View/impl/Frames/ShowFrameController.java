package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.IdTableCell;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.IncreaseTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainFrameController.class);

    @FXML
    private TableView<Position> table;

    private static final int TABLE_ID = 0;
    private static final int TABLE_NAME = 1;
    private static final int TABLE_PRICE = 2;
    private static final int TABLE_INCREASE = 3;
    private static final int TABLE_MAX = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        setTable();

        update();
    }

    @SuppressWarnings("unchecked")
    private void setTable() {
        for (int i = 0; i < TABLE_MAX; i++) {
            table.getColumns().add(new TableColumn<>());
        }

        TableColumn column;

        column = table.getColumns().get(TABLE_ID);
        column.setText("");
        column.setCellFactory(IdTableCell.getCellFactory());
        column.setCellValueFactory(new PropertyValueFactory<Position, Boolean>("use"));

        column = table.getColumns().get(TABLE_NAME);
        column.setText("Наименование");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("name"));

        column = table.getColumns().get(TABLE_PRICE);
        column.setText("Текущая цена");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("price"));

        column = table.getColumns().get(TABLE_INCREASE);
        column.setText("Тренд");
        column.setCellFactory(IncreaseTableCell.getCellFactory());
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("increase"));

        table.getSelectionModel().setCellSelectionEnabled(false);
    }

    private void addListeners() {

    }

    private void update() {
        Platform.runLater(() -> {
            List<Position> positions = ServiceFactory.getInstance().getPositionService().getElements();

            List<Position> selectedItems = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            // Очистка таблицы
            int rows = table.getItems().size();
            if (rows > 0) {
                table.getItems().subList(0, rows).clear();
            }

            ObservableList<Position> data = FXCollections.observableArrayList();

            for (Position position : positions) {
                if (VarUtils.getBoolean(position.getUse())) {
                    data.add(position);
                }
            }

            table.setItems(data);
            table.refresh();

            for (Position position : selectedItems) {
                table.getSelectionModel().select(position);
                table.getSelectionModel().selectFirst();
            }
        });
    }
}
