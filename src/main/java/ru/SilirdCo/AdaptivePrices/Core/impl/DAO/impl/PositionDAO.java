package ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.DAO.BaseDAO;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;

public class PositionDAO extends BaseDAO<Position> {
    public PositionDAO() {
        super(Position.class, Position::new);
        orderAttribute = "name";
    }
}
