package ru.SilirdCo.AdaptivePrices.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.LaunchCore;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.ServiceFactory;

import java.util.Arrays;

public class AdaptivePricesTest {
    private static final Logger logger = LoggerFactory.getLogger(AdaptivePricesTest.class);

    public static void main(String[] args) {
        if (!LaunchCore.launch()) {
            logger.error("Ошибка запуска ядра");
            System.exit(1);
        }

        ServiceFactory.getInstance()
                .getAdaptivePriceService()
                .reset();

        ServiceFactory.getInstance()
                .getAdaptivePriceService()
                .calculate();

        /*
        User user = new User();
        user.setName("Admin");
        user.setAdmin(true);

        ServiceFactory.getInstance()
                .getUserService()
                .save(user);

        System.out.println(Arrays.toString(ServiceFactory.getInstance()
                .getUserService()
                .getElements().toArray()));
                */

        /*
        Position position1 = new Position();
        position1.setName("Beer1");
        position1.setPrice(100F);
        position1.setMinPrice(10F);
        position1.setMaxPrice(1000F);
        position1.setUse(true);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position1);

        Position position2 = new Position();
        position2.setName("Beer2");
        position2.setPrice(500F);
        position2.setMinPrice(50F);
        position2.setMaxPrice(5000F);
        position2.setUse(true);

        ServiceFactory.getInstance()
                .getPositionService()
                .save(position2);

        System.out.println(Arrays.toString(ServiceFactory.getInstance()
                .getPositionService()
                .getElements().toArray()));
                */
    }
}
