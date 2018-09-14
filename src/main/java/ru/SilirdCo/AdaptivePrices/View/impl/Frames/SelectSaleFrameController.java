package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TextField.FloatTextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class SelectSaleFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(SelectSaleFrameController.class);

    @FXML
    private TextField textSales;
    private FloatTextField floatSales;

    @FXML
    private Button butAccept;
    @FXML
    private Button butCancel;

    private Consumer<Float> selectAction;

    private static final int TABLE_ID = 0;
    private static final int TABLE_NAME = 1;
    private static final int TABLE_PRICE = 2;
    private static final int TABLE_MAX = 3;

    public SelectSaleFrameController(Consumer<Float> selectAction) {
        this.selectAction = selectAction;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        floatSales = new FloatTextField(textSales);

        addListeners();
    }

    private void addListeners() {
        butAccept.setOnAction(event -> {
            float sales = floatSales.getFloat();
            close();
            selectAction.accept(sales);
        });

        butCancel.setOnAction(event -> close());
    }
}
