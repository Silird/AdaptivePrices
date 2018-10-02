package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.Events.SenderFactory;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventSender;
import ru.SilirdCo.AdaptivePrices.View.impl.Launch.MainJavaFX;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.FrameFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SalesFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainFrameController.class);

    @FXML
    private TableView<Sale> table;

    @FXML
    private Button butAdd;

    @FXML
    private Button butRecord;
    @FXML
    private Button butCancel;

    private static final int TABLE_NAME = 0;
    private static final int TABLE_SALES = 1;
    private static final int TABLE_MAX = 2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        setTable();
    }

    @SuppressWarnings("unchecked")
    private void setTable() {
        for (int i = 0; i < TABLE_MAX; i++) {
            table.getColumns().add(new TableColumn<>());
        }

        TableColumn column;

        column = table.getColumns().get(TABLE_NAME);
        column.setText("Позиция");
        column.setCellValueFactory(new PropertyValueFactory<Sale, Position>("position"));

        column = table.getColumns().get(TABLE_SALES);
        column.setText("Количества продаж");
        column.setCellValueFactory(new PropertyValueFactory<Position, String>("sales"));

        table.getSelectionModel().setCellSelectionEnabled(false);
    }

    private void addListeners() {
        butAdd.setOnAction(event -> FrameFactory.getInstance()
                .getSelectFrame(position -> FrameFactory.getInstance()
                        .getSelectSalesFrame(sales -> {
                            if (position != null) {
                                Sale existingSale = null;
                                for (Sale sale : table.getItems()) {
                                    if (Objects.equals(sale.getPosition().getId(), position.getId())) {
                                        existingSale = sale;
                                        break;
                                    }
                                }

                                if (existingSale == null) {
                                    Sale sale = new Sale();
                                    sale.setPosition(position);
                                    sale.setSales(sales);

                                    table.getItems().add(sale);
                                }
                                else {
                                    existingSale.setSales(VarUtils.getFloat(existingSale.getSales()) +
                                            VarUtils.getFloat(sales));

                                    table.refresh();
                                }
                            }
                        }, getFrame()), getFrame()));

        butRecord.setOnAction(event -> {
            for (Sale sale : table.getItems()) {
                if (sale.getPosition() != null) {
                    Position position = ServiceFactory.getInstance()
                            .getPositionService()
                            .findById(sale.getPosition()
                                    .getId());
                    position.setSales(VarUtils.getFloat(position.getSales()) + sale.getSales());
                    ServiceFactory.getInstance()
                            .getPositionService()
                            .save(position);
                }
            }
            ServiceFactory.getInstance().getAdaptivePriceService().calculate();
            EventSender.sendUpdate();
            close();
        });

        butCancel.setOnAction(event -> close());
    }
}
