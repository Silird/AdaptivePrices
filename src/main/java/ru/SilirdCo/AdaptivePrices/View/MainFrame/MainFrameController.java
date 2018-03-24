package ru.SilirdCo.AdaptivePrices.View.MainFrame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {
    @FXML
    TextField textText;

    @FXML
    CheckBox checkCheck;

    @FXML
    Button butExit;
    @FXML
    Button butOk;
    @FXML
    Button butCheck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        butExit.setOnAction(event -> System.exit(0));

        butCheck.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (checkCheck.isSelected()) {
                alert.setContentText("Галочка поставлена");
            }
            else {
                alert.setContentText("Галочка не поставлена");
            }

            alert.show();
        });

        butOk.setOnAction(event -> {
            textText.setText("Была нажата кнопка \"ОК\"");
        });
    }
}
