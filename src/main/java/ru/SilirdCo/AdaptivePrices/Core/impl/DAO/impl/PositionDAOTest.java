package ru.SilirdCo.AdaptivePrices.Core.impl.DAO.impl;

import ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.ElementAttribute;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.Core.interfaces.IDAO;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAOTest implements IDAO<Position> {
    private List<Position> positions = new ArrayList<>();

    @Override
    public Position update(Position entity) throws PersistenceException {
        if (entity.getId() == null) {
            entity.setId(getMaxId() + 1);
            positions.add(entity);
        }
        else {
            Position editPosition = getByID(entity.getId());
            if (editPosition != null) {
                positions.remove(editPosition);
                positions.add(entity);
            }
            else {
                positions.add(entity);
            }
        }
        return null;
    }

    @Override
    public void remove(Position entity) throws PersistenceException {
        remove(entity.getId());
    }

    @Override
    public void remove(Integer id) throws PersistenceException {
        Position position = getByID(id);
        if (position != null) {
            positions.remove(position);
        }
    }

    @Override
    public Position getByID(Integer id) throws PersistenceException {
        if (id != null) {
            for (Position position : positions) {
                if (position.getId().equals(id)) {
                    return position;
                }
            }
        }
        return null;
    }

    @Override
    public Position getFirstResult() throws PersistenceException {
        if (!positions.isEmpty()) {
            return positions.get(0);
        }
        return null;
    }

    @Override
    public List<Position> getAll() throws PersistenceException {
        return new ArrayList<>(positions);
    }

    @Override
    public List<Position> getByAttribute(List<ElementAttribute> attribute) throws PersistenceException {
        return null;
    }

    private int getMaxId() {
        int id = 0;
        for (Position position : positions) {
            if (VarUtils.getInteger(position.getId()) > id) {
                id = VarUtils.getInteger(position.getId());
            }
        }

        return id;
    }
}
