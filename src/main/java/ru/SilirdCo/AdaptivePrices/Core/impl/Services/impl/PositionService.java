package ru.SilirdCo.AdaptivePrices.Core.impl.Services.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.BaseService;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.DAOFactory;

public class PositionService extends BaseService<Position> {
    public PositionService() {
        super(DAOFactory.getInstance()
                .getPrositionDAO());
    }
}
