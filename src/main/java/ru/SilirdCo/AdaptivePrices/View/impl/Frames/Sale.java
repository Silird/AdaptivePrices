package ru.SilirdCo.AdaptivePrices.View.impl.Frames;

import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;

public class Sale {
    private Position position;
    private Float sales;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getSales() {
        return sales;
    }

    public void setSales(Float sales) {
        this.sales = sales;
    }
}
