package ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Util.ExceptionHandler;
import ru.SilirdCo.AdaptivePrices.View.impl.Frames.*;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel.CommonPanel;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel.EmptyPanel;

import java.io.IOException;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public class PanelFactory {
    private static final Logger logger = LoggerFactory.getLogger(PanelFactory.class);

    private static PanelFactory instance;

    public static PanelFactory getInstance() {
        if (instance == null) {
            instance = new PanelFactory();
        }
        return instance;
    }

    public CommonPanel<MainFrameController> getMainPanel() {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/MainFrame.fxml"));

        MainFrameController controller = new MainFrameController();
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }

    public CommonPanel<CreatePositionFrameController> getCreatePositionPanel(Position position) {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/CreatePositionFrame.fxml"));

        CreatePositionFrameController controller = new CreatePositionFrameController(position);
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }

    public CommonPanel<ShowFrameController> getShowPanel() {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/ShowFrame.fxml"));

        ShowFrameController controller = new ShowFrameController();
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }

    public CommonPanel<SelectFrameController> getSelectPanel(Consumer<Position> selectAction) {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/SelectFrame.fxml"));

        SelectFrameController controller = new SelectFrameController(selectAction);
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }

    public CommonPanel<SelectSaleFrameController> getSelectSalesPanel(Consumer<Float> selectAction) {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/SelectSaleFrame.fxml"));

        SelectSaleFrameController controller = new SelectSaleFrameController(selectAction);
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }

    public CommonPanel<SalesFrameController> getSalesPanel() {
        Node content;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frames/SalesFrame.fxml"));

        SalesFrameController controller = new SalesFrameController();
        loader.setController(controller);

        try {
            content = loader.load();
        }
        catch (IOException ex) {
            ExceptionHandler.handle(logger, ex);
            content = EmptyPanel.get();
        }

        return new CommonPanel<>(controller, content);
    }
}
