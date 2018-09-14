package ru.SilirdCo.AdaptivePrices.View.impl.Util.Panel;

import javafx.scene.Node;

@SuppressWarnings("WeakerAccess")
public class CommonPanel<CONTROLLER> {

    private CONTROLLER controller;

    private Node panel;

    public CommonPanel() {

    }

    public CommonPanel(CONTROLLER controller, Node panel) {
        this.controller = controller;
        this.panel = panel;
    }

    public Node getPanel() {
        return panel;
    }

    public void setPanel(Node panel) {
        this.panel = panel;
    }

    public CONTROLLER getController() {
        return controller;
    }

    public void setController(CONTROLLER controller) {
        this.controller = controller;
    }
}
