package ru.SilirdCo.AdaptivePrices.Core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.DAOFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.PersistenceUtil;
import ru.SilirdCo.AdaptivePrices.Util.ExceptionHandler;

public class LaunchCore {
    private static final Logger logger = LoggerFactory.getLogger(LaunchCore.class);

    public static boolean launch() {
        logger.info("\n\nЗапуск ядра\n\n");

        try {
            logger.info("\n\nИнициализация сессии..\n\n");

            if (!PersistenceUtil.initSession()) {
                return false;
            }

            logger.info("\n\nИнициализация фабрик..\n\n");
            DAOFactory.getInstance();
            ServiceFactory.getInstance();

            addTestPositions();
        }
        catch (Exception ex) {
            ExceptionHandler.handle(logger, ex);

            return false;
        }

        return true;
    }

    private static void addTestPositions() {

        Position position = new Position();
        position.setId(null);
        position.setName("Дорогое пиво");
        position.setMaxPrice(100F);
        position.setMinPrice(20F);
        position.setUse(true);
        position.setPrice(50F);
        position.setSales(0F);
        position.setIncrease(null);
        position.setDefaultPrice(50F);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position);

        position = new Position();
        position.setId(null);
        position.setName("Обычное пиво");
        position.setMaxPrice(80F);
        position.setMinPrice(10F);
        position.setUse(true);
        position.setPrice(30F);
        position.setSales(0F);
        position.setIncrease(null);
        position.setDefaultPrice(30F);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position);

        position = new Position();
        position.setId(3);
        position.setName("Второе обычное пиво");
        position.setMaxPrice(90F);
        position.setMinPrice(5F);
        position.setUse(true);
        position.setPrice(30F);
        position.setSales(0F);
        position.setIncrease(null);
        position.setDefaultPrice(30F);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position);

        position = new Position();
        position.setId(4);
        position.setName("Очень дорогое пиво");
        position.setMaxPrice(200F);
        position.setMinPrice(30F);
        position.setUse(true);
        position.setPrice(100F);
        position.setSales(0F);
        position.setIncrease(null);
        position.setDefaultPrice(100F);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position);
    }
}
