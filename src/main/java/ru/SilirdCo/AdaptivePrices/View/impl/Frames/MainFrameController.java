package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.Structure;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventSender;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventTransport;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.Filters.UpdateFilter;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.FrameFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.UseTableCell;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.IncreaseTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFrameController extends BaseController implements Initializable {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MainFrameController.class);

    @FXML
    private TableView<Position> table;

    @FXML
    private HBox buttons;
    @FXML
    private Button butAdd;
    @FXML
    private Button butEdit;
    @FXML
    private Button butUsers;
    @FXML
    private Button butChangeUser;
    @FXML
    private Button butReset;
    @FXML
    private Button butSales;
    @FXML
    private Button butShow;

    private static final int TABLE_ID = 0;
    private static final int TABLE_NAME = 1;
    private static final int TABLE_PRICE = 2;
    private static final int TABLE_DEFAULT_TABLE = 3;
    private static final int TABLE_INCREASE = 4;
    private static final int TABLE_MAX = 5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSubscriptions();
        addListeners();
        setTable();
        initPermissions();

        update();
    }

    private void initPermissions() {
        if (!Structure.admin) {
            buttons.getChildren().remove(butAdd);
            buttons.getChildren().remove(butUsers);
        }
    }

    private void initSubscriptions() {
        EventTransport.getInstance().getObservable()
                .filter(new UpdateFilter())
                .subscribe(event -> update());
    }

    @SuppressWarnings("unchecked")
    private void setTable() {
        for (int i = 0; i < TABLE_MAX; i++) {
            table.getColumns().add(new TableColumn<>());
        }

        TableColumn column;

        column = table.getColumns().get(TABLE_ID);
        column.setText("");
        column.setCellFactory(UseTableCell.getCellFactory());
        column.setCellValueFactory(new PropertyValueFactory<Position, Boolean>("use"));

        column = table.getColumns().get(TABLE_NAME);
        column.setText("Наименование");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("name"));

        column = table.getColumns().get(TABLE_PRICE);
        column.setText("Текущая цена");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("price"));

        column = table.getColumns().get(TABLE_DEFAULT_TABLE);
        column.setText("Стартовая цена");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("defaultPrice"));

        column = table.getColumns().get(TABLE_INCREASE);
        column.setText("Тренд");
        column.setCellFactory(IncreaseTableCell.getCellFactory());
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("increase"));

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addListeners() {

        butAdd.setOnAction(event -> FrameFactory.getInstance()
                .openCreatePositionFrame(null, getFrame()));

        butEdit.setOnAction(event -> {
            if (table.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Не выбран элемент из таблицы");
                alert.show();
            }
            else if (table.getSelectionModel().getSelectedItems().size() > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Выбрано несколько элементов из таблицы");
                alert.show();
            }
            else {
                Position position = table.getSelectionModel().getSelectedItem();
                FrameFactory.getInstance()
                        .openCreatePositionFrame(position, getFrame());
            }
        });
        table.setOnMouseClicked(t -> {
            if (t.getClickCount() == 2 && !table.getSelectionModel().isEmpty()) {
                Position position = table.getSelectionModel().getSelectedItem();
                FrameFactory.getInstance()
                        .openCreatePositionFrame(position, getFrame());
            }
        });

        butUsers.setOnAction(event -> FrameFactory.getInstance()
                .openUserMainFrame(getFrame()));

        butChangeUser.setOnAction(event -> {
            super.close();
            FrameFactory.getInstance()
                    .openAuthFrame();
        });

        butSales.setOnAction(event -> FrameFactory.getInstance()
                .openSalesFrame(getFrame()));

        butReset.setOnAction(event -> {
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены, что хотите сбросить цены?\n");
            conf.setTitle("Внимание");
            conf.setHeaderText("Проверка");
            conf.getButtonTypes().clear();
            ButtonType ok = new ButtonType("Да");
            conf.getButtonTypes().add(ok);
            ButtonType cancel = new ButtonType("Отмена");
            conf.getButtonTypes().add(cancel);

            Optional<ButtonType> result = conf.showAndWait();

            if (result.isPresent() && result.get().equals(ok))  {
                ServiceFactory.getInstance()
                        .getAdaptivePriceService()
                        .reset();
                EventSender.sendUpdate();
            }
        });

        butShow.setOnAction(event -> FrameFactory.getInstance()
                .openShowFrame());
    }

    public void update() {
        Platform.runLater(() -> {
            List<Position> positions = ServiceFactory.getInstance().getPositionService().getElements();

            List<Position> selectedItems = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            // Очистка таблицы
            int rows = table.getItems().size();
            if (rows > 0) {
                table.getItems().subList(0, rows).clear();
            }

            ObservableList<Position> data = FXCollections.observableArrayList(positions);

            logger.info("" + data.size());

            table.setItems(data);
            table.refresh();

            for (Position position : selectedItems) {
                table.getSelectionModel().select(position);
                table.getSelectionModel().selectFirst();
            }
        });
    }

    @Override
    public void close() {
        super.close();
        Platform.exit();
        System.exit(0);
    }
}
