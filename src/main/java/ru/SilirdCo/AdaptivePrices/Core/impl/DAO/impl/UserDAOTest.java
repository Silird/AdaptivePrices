package ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.ElementAttribute;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.User;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IDAO;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOTest implements IDAO<User> {
    private List<User> users = new ArrayList<>();

    @Override
    public User update(User entity) throws PersistenceException {
        if (entity.getId() == null) {
            entity.setId(getMaxId() + 1);
            users.add(entity);
        }
        else {
            User editUser = getByID(entity.getId());
            if (editUser != null) {
                users.remove(editUser);
                users.add(entity);
            }
            else {
                users.add(entity);
            }
        }
        return null;
    }

    @Override
    public void remove(User entity) throws PersistenceException {
        remove(entity.getId());
    }

    @Override
    public void remove(Integer id) throws PersistenceException {
        User user = getByID(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public User getByID(Integer id) throws PersistenceException {
        if (id != null) {
            for (User user : users) {
                if (user.getId().equals(id)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public User getFirstResult() throws PersistenceException {
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws PersistenceException {
        return new ArrayList<>(users);
    }

    @Override
    public List<User> getByAttribute(List<ElementAttribute> attribute) throws PersistenceException {
        return null;
    }

    private int getMaxId() {
        int id = 0;
        for (User user : users) {
            if (VarUtils.getInteger(user.getId()) > id) {
                id = VarUtils.getInteger(user.getId());
            }
        }

        return id;
    }
}
