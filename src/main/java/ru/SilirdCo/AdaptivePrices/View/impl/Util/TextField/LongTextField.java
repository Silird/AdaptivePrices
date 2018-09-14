package ru.SilirdCo.AdaptivePrices.View.impl.Util.TextField;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongTextField {
    private final static Logger logger = LoggerFactory.getLogger(LongTextField.class);

    private TextField textField = null;

    private ObjectProperty<Long> LongProp = null;

    public final ObjectProperty<Long> LongProperty() {
        if (LongProp == null) {
            LongProp = new SimpleObjectProperty<>();
            LongProp.setValue(null);

        }
        return LongProp;
    }

    public LongTextField(TextField textField) {
        this.textField = textField;
        initListeners();
    }

    private void initListeners() {
        if (textField != null) {

            textField.setAlignment(Pos.CENTER_RIGHT);

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("")) {
                    try {
                        Long result = Long.parseLong(newValue);

                        LongProperty().setValue(result);
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

    public void setLong(Long value) {
        if (value == null) {
            textField.setText("");
        }
        else {
            textField.setText(String.valueOf(value));
        }
    }
}
