package ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory;

import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.View.impl.Frames.*;
import ru.SilirdCo.AdaptivePrices.View.impl.Launch.MainJavaFX;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel.CommonPanel;

import java.util.function.Consumer;

public class FrameFactory {
    private static final Logger logger = LoggerFactory.getLogger(FrameFactory.class);

    private static FrameFactory instance;

    public static FrameFactory getInstance() {
        if (instance == null) {
            instance = new FrameFactory();
        }
        return instance;
    }

    public void openMainFrame() {
        CommonPanel<MainFrameController> panel = PanelFactory.getInstance()
                .getMainPanel();

        MainJavaFX.openFrame(panel, "Главное меню");
    }

    public void openCreatePositionFrame(Position position, Stage owner) {
        CommonPanel<CreatePositionFrameController> panel = PanelFactory.getInstance()
                .getCreatePositionPanel(position);

        MainJavaFX.openDialog(panel, "Обновление позиции", owner);
    }

    public void openShowFrame() {
        CommonPanel<ShowFrameController> panel = PanelFactory.getInstance()
                .getShowPanel();

        MainJavaFX.openFrame(panel, "Меню");
    }

    public void getSelectFrame(Consumer<Position> selectAction, Stage owner) {
        CommonPanel<SelectFrameController> panel = PanelFactory.getInstance()
                .getSelectPanel(selectAction);

        MainJavaFX.openDialog(panel, "Выбор позиции", owner);
    }

    public void getSelectSalesFrame(Consumer<Float> selectAction, Stage owner) {
        CommonPanel<SelectSaleFrameController> panel = PanelFactory.getInstance()
                .getSelectSalesPanel(selectAction);

        MainJavaFX.openDialog(panel, "Ввод продаж позиции", owner);
    }

    public void openSalesFrame(Stage owner) {
        CommonPanel<SalesFrameController> panel = PanelFactory.getInstance()
                .getSalesPanel();

        MainJavaFX.openDialog(panel, "Ввод продаж", owner);
    }

    public void openUserMainFrame(Stage owner) {
        CommonPanel<UserMainFrameController> panel = PanelFactory.getInstance()
                .getUserMainPanel();

        MainJavaFX.openDialog(panel, "Пользователи", owner);
    }

    public void openCreateUserFrame(User user, Stage owner) {
        CommonPanel<CreateUserFrameController> panel = PanelFactory.getInstance()
                .getCreateUserPanel(user);

        MainJavaFX.openDialog(panel, "Обновление пользователя", owner);
    }

    public void openAuthFrame() {
        CommonPanel<AuthFrameController> panel = PanelFactory.getInstance()
                .getAuthPanel();

        MainJavaFX.openFrame(panel, "Авторизация");
    }
}
