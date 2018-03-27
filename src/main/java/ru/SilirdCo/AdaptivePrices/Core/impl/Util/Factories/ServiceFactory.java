package ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.AdaptivePriceService;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.impl.*;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IAdaptivePriceService;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IService;

public class ServiceFactory {
    private ApplicationContext context = new ClassPathXmlApplicationContext("Factories/ServiceFactory.xml");

    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public IAdaptivePriceService getAdaptivePriceService() {
        return context.getBean("adaptivePriceService", AdaptivePriceService.class);
    }

    public IService<User> getUserService() {
        return context.getBean("userService", UserService.class);
    }

    public IService<Position> getPositionService() {
        return context.getBean("positionService", PositionService.class);
    }

}
