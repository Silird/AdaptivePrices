package ru.SilirdCo.AdaptivePrices.Core.impl.Entities;

import ru.SilirdCo.AdaptivePrices.Core.interfaces.IEntity;

public class BaseEntity implements IEntity {
    /**
     * Конструктор сущности
     */
    public BaseEntity() {
    }

    /**
     * Получение Id сущности
     * @return - Id сущности
     */
    @Override
    public Integer getId() {
        return null;
    }

    /**
     * Установка Id сущности
     * @param id - устанавливаемый Id
     */
    @Override
    public void setId(Integer id) {
    }
}
