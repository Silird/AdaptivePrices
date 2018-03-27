package ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.DAO.BaseDAO;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class, User::new);
        orderAttribute = "name";
    }
}
