package ru.SilirdCo.AdaptivePrices.View.impl.Launch;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.EventTransport;
import ru.SilirdCo.AdaptivePrices.View.impl.Events.Filters.WarnFilter;
import ru.SilirdCo.AdaptivePrices.View.impl.Frames.BaseController;
import ru.SilirdCo.AdaptivePrices.View.impl.Frames.MainFrameController;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.FrameFactory;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel.CommonPanel;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.Factory.PanelFactory;

public class MainJavaFX extends Application {
    private static final Logger logger = LoggerFactory.getLogger(MainJavaFX.class);

    public static MainFrameController mainFrameController;
    public static Scene scene;
    public static Stage primaryStage;

    public static void show(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        EventTransport.getInstance().getObservable()
                .filter(new WarnFilter())
                .subscribe(event -> warningFrame(event.getMessage()));

        FrameFactory.getInstance().openAuthFrame();
        //CommonPanel<MainFrameController> commonPanel = PanelFactory.getInstance().getMainPanel();

        //openFrame(commonPanel, "Авторизация");
        /*
        mainFrameController = commonPanel.getController();

        Node mainFrameNode = commonPanel.getPanel();

        MainJavaFX.primaryStage = primaryStage;
        scene = new Scene((Parent) mainFrameNode);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Главная форма");
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> {
            Platform.exit();
            System.exit(0);
        });
        */
    }

    private void warningFrame(String message){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText(message);

            alert.showAndWait();
        });
    }

    public static void openFrame(CommonPanel commonPanel, String title) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            Scene scene = new Scene((Parent) commonPanel.getPanel());
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

            if (commonPanel.getController() != null) {
                if (commonPanel.getController() instanceof BaseController) {
                    ((BaseController) commonPanel.getController()).setFrame(stage);
                }
            }
        });
    }

    public static void openDialog(CommonPanel commonPanel, String title) {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle(title);
            //stage.initStyle(StageStyle.UTILITY);
            stage.initOwner(MainJavaFX.primaryStage);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene((Parent) commonPanel.getPanel());
            stage.setScene(scene);

            if (commonPanel.getController() != null) {
                if (commonPanel.getController() instanceof BaseController) {
                    ((BaseController) commonPanel.getController()).setFrame(stage);
                }
            }

            stage.showAndWait();
            //stage.show();
        });
    }
}
