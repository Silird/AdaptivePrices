package ru.SilirdCo.AdaptivePrices.Core.impl.Services.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.impl.Services.BaseService;
import ru.SilirdCo.AdaptivePrices.Core.impl.Util.Factories.DAOFactory;

public class UserService extends BaseService<User> {
    public UserService() {
        super(DAOFactory.getInstance()
                .getUserDAO());
    }
}
