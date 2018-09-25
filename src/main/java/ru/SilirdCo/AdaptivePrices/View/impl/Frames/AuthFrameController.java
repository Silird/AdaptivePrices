package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.Structure;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventSender;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.FrameFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(AuthFrameController.class);

    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPassword;

    @FXML
    private Button butEnter;
    @FXML
    private Button butExit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addListeners();
    }

    private boolean requiredFields() {

        boolean check = true;
        String message = "Ошибка:";
        if(VarUtils.getString(textName.getText()).equals("")) {
            message += "\n Не заполнено поле: логин";
            check = false;
        }
        if(VarUtils.getString(textPassword.getText()).equals("")) {
            message += "\n Не заполнено поле: пароль";
            check = false;
        }

        if (!check) {
            EventSender.sendWarn(message);
        }

        return check;
    }

    private void addListeners() {
        butEnter.setOnAction(event -> auth());
        textName.setOnAction(event -> auth());
        textPassword.setOnAction(event -> auth());

        butExit.setOnAction(event -> close());
    }

    private void auth() {
        if (requiredFields()) {
            List<User> users = ServiceFactory.getInstance()
                    .getUserService()
                    .getElements();

            for (User user : users) {
                if ((VarUtils.getString(textName.getText()).equals(user.getName())) &&
                        (VarUtils.getString(textPassword.getText()).equals(user.getPassword()))) {
                    Structure.admin = VarUtils.getBoolean(user.getAdmin());
                    super.close();
                    FrameFactory.getInstance().openMainFrame();
                    return;
                }
            }

            EventSender.sendWarn("Пара логина и пароля не совпадают");
        }
    }

    @Override
    public void close() {
        super.close();
        Platform.exit();
        System.exit(0);
    }
}
