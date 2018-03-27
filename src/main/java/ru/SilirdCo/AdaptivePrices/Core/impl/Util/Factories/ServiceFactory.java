package ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.impl.*;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IService;

public class ServiceFactory {
    private ApplicationContext context = new ClassPathXmlApplicationContext("Factories/Services/Generated/ServiceFactory.xml");

    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public IService<User> getUser1Service() {
        return context.getBean("userService", UserService.class);
    }

    public IService<Position> getProfileService() {
        return context.getBean("positionService", PositionService.class);
    }

}
