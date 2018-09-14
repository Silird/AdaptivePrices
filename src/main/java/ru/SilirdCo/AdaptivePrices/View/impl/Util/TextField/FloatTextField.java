package ru.SilirdCo.AdaptivePrices.View.impl.Util.TextField;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;

public class FloatTextField {
    private final static Logger logger = LoggerFactory.getLogger(FloatTextField.class);

    private TextField textField = null;

    private ObjectProperty<Float> floatProp = null;

    public final ObjectProperty<Float> floatProperty() {
        if (floatProp == null) {
            floatProp = new SimpleObjectProperty<>();
            floatProp.setValue(null);

        }
        return floatProp;
    }

    public FloatTextField(TextField textField) {
        this.textField = textField;
        initListeners();
    }

    private void initListeners() {
        if (textField != null) {

            textField.setAlignment(Pos.CENTER_RIGHT);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    try {
                        Float result = Float.parseFloat(newValue);

                        floatProperty().setValue(VarUtils.roundFloat(result));
                    }
                    catch (NumberFormatException ex) {
                        textField.setText(oldValue);
                    }
                }
            });
        }
        else {
            logger.warn("Текстовое поле не назначено");
        }
    }

    public Float getFloat() {
        /*
        Float result = null;
        if (textField != null) {
            if (!textField.getText().equals("")) {
                try {
                    result = Float.parseFloat(textField.getText());
                }
                catch (NumberFormatException ex) {
                    ExceptionHandler.handle(logger, ex);
                    result = null;
                }
            }
        }
        else {
            logger.warn("Текстовое поле не назначено");
        }

        return result;
        */

        return floatProperty().get();
    }

    public void setFloat(Float value) {
        if (value == null) {
            textField.setText("");
        }
        else {
            textField.setText(String.valueOf(value));
        }
    }
}
