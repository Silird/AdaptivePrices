package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import javafx.stage.Stage;

public class BaseController {
    private Stage frame;

    public Stage getFrame() {
        return frame;
    }

    public void setFrame(Stage frame) {
        this.frame = frame;
    }

    public void close() {
        frame.close();
    }
}
