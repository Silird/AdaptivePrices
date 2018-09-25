package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventTransport;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.Filters.UpdateFilter;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.FrameFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.BooleanTableCell;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell.IdTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserMainFrameController extends BaseController implements Initializable {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserMainFrameController.class);

    @FXML
    private TableView<User> table;

    @FXML
    private Button butAdd;
    @FXML
    private Button butEdit;

    private static final int TABLE_ID = 0;
    private static final int TABLE_NAME = 1;
    private static final int TABLE_ADMIN = 2;
    private static final int TABLE_MAX = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSubscriptions();
        addListeners();
        setTable();

        update();
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
        column.setCellFactory(IdTableCell.getCellFactory());
        column.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));

        column = table.getColumns().get(TABLE_NAME);
        column.setText("Наименование");
        column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        column = table.getColumns().get(TABLE_ADMIN);
        column.setText("Администратор");
        column.setCellValueFactory(new PropertyValueFactory<User, Boolean>("admin"));
        column.setCellFactory(BooleanTableCell.getCellFactory());

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addListeners() {

        butAdd.setOnAction(event -> FrameFactory.getInstance()
                .openCreateUserFrame(null));

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
                User user = table.getSelectionModel().getSelectedItem();
                FrameFactory.getInstance()
                        .openCreateUserFrame(user);
            }
        });
        table.setOnMouseClicked(t -> {
            if (t.getClickCount() == 2 && !table.getSelectionModel().isEmpty()) {
                User user = table.getSelectionModel().getSelectedItem();
                FrameFactory.getInstance()
                        .openCreateUserFrame(user);
            }
        });
    }

    public void update() {
        Platform.runLater(() -> {
            List<User> users = ServiceFactory.getInstance().getUserService().getElements();

            List<User> selectedItems = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            // Очистка таблицы
            int rows = table.getItems().size();
            if (rows > 0) {
                table.getItems().subList(0, rows).clear();
            }

            ObservableList<User> data = FXCollections.observableArrayList(users);

            table.setItems(data);
            table.refresh();

            for (User user : selectedItems) {
                table.getSelectionModel().select(user);
                table.getSelectionModel().selectFirst();
            }
        });
    }
}
