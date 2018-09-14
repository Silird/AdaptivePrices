package ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("WeakerAccess")
public class EmptyPanel {
    public static Node get() {
        AnchorPane result = new AnchorPane();
        result.getChildren().add(new Label("Форма не инициализирована, критическая ошибка"));

        return result;
    }
}
