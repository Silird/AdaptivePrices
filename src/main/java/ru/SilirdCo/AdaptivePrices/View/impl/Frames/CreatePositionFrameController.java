package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventSender;
import ru.SilirdCo.AdaptivePrices.View.impl.Launch.MainJavaFX;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.TextField.FloatTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatePositionFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(CreatePositionFrameController.class);


    @FXML
    private Label labelTitle;
    @FXML
    private TextField textName;
    @FXML
    private TextField testDefaultPrice;
    private FloatTextField floatDefaultPrice;
    @FXML
    private TextField textMinPrice;
    private FloatTextField floatMinPrice;
    @FXML
    private TextField textMaxPrice;
    private FloatTextField floatMaxPrice;
    @FXML
    private CheckBox checkUse;

    @FXML
    private Button butRecord;
    @FXML
    private Button butAccept;
    @FXML
    private Button butCancel;

    private Position position = null;

    public CreatePositionFrameController(Position position) {
        this.position = position;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        floatDefaultPrice = new FloatTextField(testDefaultPrice);
        floatMinPrice = new FloatTextField(textMinPrice);
        floatMaxPrice = new FloatTextField(textMaxPrice);

        setPosition(position);

        addListeners();
    }

    private boolean requiredFields() {

        boolean check = true;
        String message = "Ошибки в заполнении полей:";
        if(VarUtils.getString(textName.getText()).equals("")) {
            message += "\n Не заполнено поле: наименование";
            check = false;
        }
        if(floatMinPrice.getFloat() > floatDefaultPrice.getFloat()) {
            message += "\n Минимальная цена больше стандартной";
            check = false;
        }
        if(floatMaxPrice.getFloat() < floatDefaultPrice.getFloat()) {
            message += "\n Максимальная цена меньше стандартной";
            check = false;
        }

        if (!check) {
            EventSender.sendWarn(message);
        }

        return check;
    }

    private void addListeners() {
        butRecord.setOnAction(event -> {
            if (requiredFields()) {
                Position position = ServiceFactory.getInstance()
                        .getPositionService()
                        .save(getPosition());
                setPosition(position);
                EventSender.sendUpdate();
            }
        });

        butAccept.setOnAction(event -> {
            if (requiredFields()) {
                Position position = ServiceFactory.getInstance()
                        .getPositionService()
                        .save(getPosition());
                setPosition(position);
                EventSender.sendUpdate();
                close();
            }
        });

        butCancel.setOnAction(event -> close());
    }

    private Position getPosition() {
        Position result = new Position();
        if (position != null) {
            result.setId(position.getId());
            result.setName(VarUtils.getString(textName.getText()));
            result.setPrice(position.getPrice());
            result.setDefaultPrice(floatDefaultPrice.getFloat());
            result.setSales(position.getSales());
            result.setMinPrice(floatMinPrice.getFloat());
            result.setMaxPrice(floatMaxPrice.getFloat());
            result.setIncrease(position.getIncrease());
            result.setUse(checkUse.isSelected());
        }
        else {
            result.setId(null);
            result.setName(VarUtils.getString(textName.getText()));
            result.setPrice(floatDefaultPrice.getFloat());
            result.setDefaultPrice(floatDefaultPrice.getFloat());
            result.setSales(0F);
            result.setMinPrice(floatMinPrice.getFloat());
            result.setMaxPrice(floatMaxPrice.getFloat());
            result.setIncrease(null);
            result.setUse(checkUse.isSelected());
        }

        return result;
    }

    private void setPosition(Position position) {
        this.position = position;
        if (position != null) {
            labelTitle.setText("Редактирование позиции");
            textName.setText(VarUtils.getString(position.getName()));
            floatDefaultPrice.setFloat(position.getDefaultPrice());
            floatMinPrice.setFloat(position.getMinPrice());
            floatMaxPrice.setFloat(position.getMaxPrice());
            checkUse.setSelected(VarUtils.getBoolean(position.getUse()));
        }
        else {
            labelTitle.setText("Добавление позиции");
            textName.setText("");
            floatDefaultPrice.setFloat(0F);
            floatMinPrice.setFloat(0F);
            floatMaxPrice.setFloat(0F);
            checkUse.setSelected(true);
        }
    }
}
