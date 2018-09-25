package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventSender;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserFrameController extends BaseController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserFrameController.class);


    @FXML
    private Label labelTitle;
    @FXML
    private TextField textName;
    @FXML
    private PasswordField textPassword;
    @FXML
    private CheckBox checkAdmin;

    @FXML
    private Button butRecord;
    @FXML
    private Button butAccept;
    @FXML
    private Button butCancel;

    private User user = null;

    public CreateUserFrameController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUser(user);

        addListeners();
    }

    private boolean requiredFields() {

        boolean check = true;
        String message = "Ошибки в заполнении полей:";
        if(VarUtils.getString(textName.getText()).equals("")) {
            message += "\n Не заполнено поле: наименование";
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
        butRecord.setOnAction(event -> {
            if (requiredFields()) {
                User user = ServiceFactory.getInstance()
                        .getUserService()
                        .save(getUser());
                setUser(user);
                EventSender.sendUpdate();
            }
        });

        butAccept.setOnAction(event -> {
            if (requiredFields()) {
                User user = ServiceFactory.getInstance()
                        .getUserService()
                        .save(getUser());
                setUser(user);
                EventSender.sendUpdate();
                close();
            }
        });

        butCancel.setOnAction(event -> close());
    }

    private User getUser() {
        User result = new User();
        if (user != null) {
            result.setId(user.getId());
            result.setName(VarUtils.getString(textName.getText()));
            result.setPassword(VarUtils.getString(textPassword.getText()));
            result.setAdmin(checkAdmin.isSelected());
        }
        else {
            result.setId(null);
            result.setName(VarUtils.getString(textName.getText()));
            result.setPassword(VarUtils.getString(textPassword.getText()));
            result.setAdmin(checkAdmin.isSelected());
        }

        return result;
    }

    private void setUser(User user) {
        this.user = user;
        if (user != null) {
            labelTitle.setText("Редактирование пользователя");
            textName.setText(VarUtils.getString(user.getName()));
            textPassword.setText(VarUtils.getString(user.getPassword()));
            checkAdmin.setSelected(VarUtils.getBoolean(user.getAdmin()));
        }
        else {
            labelTitle.setText("Добавление пользователя");
            textName.setText("");
            textPassword.setText("");
            checkAdmin.setSelected(true);
        }
    }
}
