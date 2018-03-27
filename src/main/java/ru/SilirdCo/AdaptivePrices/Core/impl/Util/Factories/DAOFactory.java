package ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl.PositionDAO;
import ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl.UserDAO;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IDAO;

public class DAOFactory {
    private ApplicationContext context = new ClassPathXmlApplicationContext("Factories/DAOFactory.xml");

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public IDAO<User> getUserDAO() {
        return context.getBean("userDAO", UserDAO.class);
    }

    public IDAO<Position> getPrositionDAO() {
        return context.getBean("positionDAO", PositionDAO.class);
    }
}
