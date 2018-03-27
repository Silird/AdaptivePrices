package ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.*;

import java.util.ArrayList;
import java.util.List;

public class UserAttribute {
    private static final Logger logger = LoggerFactory.getLogger(PositionAttribute.class);

    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int ADMIN = 2;


    public static ElementAttribute getAttribute(int type, int field, String value)  {
        switch (field) {
            case ID: {
                try {
                    Integer castValue = Integer.parseInt(value);
                    return new ElementAttributeOne<>(type, "id", castValue);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case NAME: {
                return new ElementAttributeOne<>(type, "name", value);
            }
            case ADMIN: {
                try {
                    Boolean castValue = Boolean.parseBoolean(value);
                    return new ElementAttributeOne<>(type, "admin", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            default: {
                logger.warn("Запроса поиска по \"" + field + "\" не найден");
                return new ElementAttributeOne<>(AttributeType.NOTHING.getID(), null, null);
            }
        }
    }
    public static ElementAttribute getAttribute(int type, int field, String value1, String value2) {
        switch (field) {
            case ID: {
                try {
                    Integer castValue1 = Integer.parseInt(value1);
                    Integer castValue2 = Integer.parseInt(value2);
                    return new ElementAttributeTwo<>(type, "id", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1+ "или значение" + value2 + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeTwo<>(AttributeType.NOTHING.getID(), null, null, null);
                }
            }
            case NAME: {
                return new ElementAttributeTwo<>(type, "name", value1, value2);
            }
            case ADMIN: {
                try {
                    Boolean castValue1 = Boolean.parseBoolean(value1);
                    Boolean castValue2 = Boolean.parseBoolean(value2);
                    return new ElementAttributeTwo<>(type, "admin", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            default: {
                logger.warn("Запроса поиска по \"" + field + "\" не найден");
                return new ElementAttributeOne<>(AttributeType.NOTHING.getID(), null, null);
            }
        }
    }
    public static ElementAttribute getAttribute(int type, int field, List<String> values) {
        switch (field) {
            case ID: {
                try {
                    List<Integer> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Integer castValue = Integer.parseInt(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "id", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case NAME: {
                return new ElementAttributeList<>(type, "name", values);
            }
            case ADMIN: {
                try {
                    List<Boolean> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Boolean castValue = Boolean.parseBoolean(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "admin", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            default: {
                logger.warn("Запроса поиска по \"" + field + "\" не найден");
                return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
            }
        }
    }
}
