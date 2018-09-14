package ru.SilirdCo.AdaptivePrices.Core.impl.Services.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.BaseService;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class PositionService extends BaseService<Position> {
    public PositionService() {
        super(DAOFactory.getInstance()
                .getPrositionDAO());
    }

    /*
    @Override
    public List<Position> getElements() {
        List<Position> result = new ArrayList<>();

        Position position = new Position();
        position.setId(1);
        position.setName("1");
        position.setMaxPrice(100F);
        position.setMinPrice(1F);
        position.setUse(true);
        position.setPrice(null);
        position.setSales(10F);
        position.setIncrease(null);
        position.setDefaultPrice(50F);

        result.add(position);

        position = new Position();
        position.setId(2);
        position.setName("2");
        position.setMaxPrice(80F);
        position.setMinPrice(10F);
        position.setUse(true);
        position.setPrice(null);
        position.setSales(5F);
        position.setIncrease(null);
        position.setDefaultPrice(30F);

        result.add(position);

        position = new Position();
        position.setId(3);
        position.setName("3");
        position.setMaxPrice(90F);
        position.setMinPrice(5F);
        position.setUse(true);
        position.setPrice(null);
        position.setSales(6F);
        position.setIncrease(null);
        position.setDefaultPrice(30F);

        result.add(position);

        position = new Position();
        position.setId(4);
        position.setName("4");
        position.setMaxPrice(200F);
        position.setMinPrice(30F);
        position.setUse(true);
        position.setPrice(null);
        position.setSales(2F);
        position.setIncrease(null);
        position.setDefaultPrice(100F);

        result.add(position);

        return result;
    }
    */
}
