package ru.SilirdCo.AdaptivePrices.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.LaunchCore;
import ru.SilirdCo.AdaptivePrices.View.impl.Launch.LaunchView;

public class AdaptivePricesMain {
    private static final Logger logger = LoggerFactory.getLogger(AdaptivePricesMain.class);

    public static void main(String[] args) {
        if (!LaunchCore.launch()) {
            logger.error("Ошибка запуска ядра");
            System.exit(1);
        }
        if (!LaunchView.launch()) {
            logger.error("Ошибка запуска формы");
            System.exit(2);
        }
    }
}
