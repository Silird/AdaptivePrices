package ru.SilirdCo.AdaptivePrices.Core.impl.Services;

import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IAdaptivePriceService;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;

import java.util.List;

public class AdaptivePriceService implements IAdaptivePriceService {
    @Override
    public boolean calculate() {
        List<Position> positions = ServiceFactory.getInstance()
                .getPositionService()
                .getElements();

        float averageSales = 0;
        int size = 0;

        for (Position position : positions) {
            if (VarUtils.getBoolean(position.getUse())) {
                size++;
                averageSales += VarUtils.getFloat(position.getSales());
            }
        }

        averageSales = averageSales/size;

        if (averageSales != 0) {
            for (Position position : positions) {
                if (VarUtils.getBoolean(position.getUse())) {
                    float coefficient = VarUtils.getFloat(position.getSales()) / averageSales;

                    float newPrice = VarUtils.getFloat(position.getDefaultPrice()) * coefficient;

                    if (newPrice > VarUtils.getFloat(position.getMaxPrice())) {
                        newPrice = VarUtils.getFloat(position.getMaxPrice());
                    }
                    else if (newPrice < VarUtils.getFloat(position.getMinPrice())) {
                        newPrice = VarUtils.getFloat(position.getMinPrice());
                    }

                    newPrice = VarUtils.roundFloat(newPrice);

                    if (newPrice > VarUtils.getFloat(position.getPrice())) {
                        position.setIncrease(true);
                    }
                    else if (newPrice < VarUtils.getFloat(position.getPrice())) {
                        position.setIncrease(false);
                    }
                    else {
                        position.setIncrease(null);
                    }

                    position.setPrice(newPrice);
                }
            }

            ServiceFactory.getInstance()
                    .getPositionService()
                    .save(positions);
        }

        return true;
    }

    @Override
    public boolean reset() {
        List<Position> positions = ServiceFactory.getInstance()
                .getPositionService()
                .getElements();

        for (Position position : positions) {
            position.setPrice(position.getDefaultPrice());
            position.setSales(30F);
            position.setIncrease(null);
        }

        ServiceFactory.getInstance()
                .getPositionService()
                .save(positions);

        return true;
    }
}
