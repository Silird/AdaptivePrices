package ru.SilirdCo.AdaptivePrices.View.impl.Util.TextField;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntTextField {
    private final static Logger logger = LoggerFactory.getLogger(IntTextField.class);

    private TextField textField = null;

    private ObjectProperty<Integer> integerProp = null;

    public final ObjectProperty<Integer> integerProperty() {
        if (integerProp == null) {
            integerProp = new SimpleObjectProperty<>();
            integerProp.setValue(null);

        }
        return integerProp;
    }

    public IntTextField(TextField textField) {
        this.textField = textField;
        initListeners();
    }

    private void initListeners() {
        if (textField != null) {

            textField.setAlignment(Pos.CENTER_RIGHT);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    try {
                        Integer result = Integer.parseInt(newValue);

                        integerProperty().setValue(result);
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

    public Integer getInteger() {
        /*
        Integer result = null;
        if (textField != null) {
            if (!textField.getText().equals("")) {
                try {
                    result = Integer.parseInt(textField.getText());
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

        return integerProperty().get();
    }

    public void setInteger(Integer value) {
        if (value == null) {
            textField.setText("");
        }
        else {
            textField.setText(String.valueOf(value));
        }
    }
}
