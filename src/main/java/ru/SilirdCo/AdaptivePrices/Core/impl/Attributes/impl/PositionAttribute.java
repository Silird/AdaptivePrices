package ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.SilirdCo.AdaptivePrices.Core.impl.Attributes.*;

import java.util.ArrayList;
import java.util.List;

public class PositionAttribute {
    private static final Logger logger = LoggerFactory.getLogger(PositionAttribute.class);

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int MAX_PRICE = 2;
    private static final int MIN_PRICE = 3;
    private static final int USE = 4;
    private static final int PRICE = 5;
    private static final int SALES = 6;
    private static final int INCREASE = 7;
    private static final int DEFAULT_PRICE = 8;


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
            case PRICE: {
                try {
                    Float castValue = Float.parseFloat(value);
                    return new ElementAttributeOne<>(type, "price", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case DEFAULT_PRICE: {
                try {
                    Float castValue = Float.parseFloat(value);
                    return new ElementAttributeOne<>(type, "defaultPrice", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case SALES: {
                try {
                    Float castValue = Float.parseFloat(value);
                    return new ElementAttributeOne<>(type, "sales", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case MAX_PRICE: {
                try {
                    Float castValue = Float.parseFloat(value);
                    return new ElementAttributeOne<>(type, "max_price", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case MIN_PRICE: {
                try {
                    Float castValue = Float.parseFloat(value);
                    return new ElementAttributeOne<>(type, "min_price", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case INCREASE: {
                try {
                    Boolean castValue = Boolean.parseBoolean(value);
                    return new ElementAttributeOne<>(type, "increase", castValue);
                }
                catch (NumberFormatException ex){
                    logger.warn("Значение \"" + value + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case USE: {
                try {
                    Boolean castValue = Boolean.parseBoolean(value);
                    return new ElementAttributeOne<>(type, "use", castValue);
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
            case MAX_PRICE: {
                try {
                    Float castValue1 = Float.parseFloat(value1);
                    Float castValue2 = Float.parseFloat(value2);
                    return new ElementAttributeTwo<>(type, "max_price", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeTwo<>(type, null, null, null);
                }
            }
            case PRICE: {
                try {
                    Float castValue1 = Float.parseFloat(value1);
                    Float castValue2 = Float.parseFloat(value2);
                    return new ElementAttributeTwo<>(type, "price", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case DEFAULT_PRICE: {
                try {
                    Float castValue1 = Float.parseFloat(value1);
                    Float castValue2 = Float.parseFloat(value2);
                    return new ElementAttributeTwo<>(type, "defaultPrice", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case SALES: {
                try {
                    Float castValue1 = Float.parseFloat(value1);
                    Float castValue2 = Float.parseFloat(value2);
                    return new ElementAttributeTwo<>(type, "sales", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case MIN_PRICE: {
                try {
                    Float castValue1 = Float.parseFloat(value1);
                    Float castValue2 = Float.parseFloat(value2);
                    return new ElementAttributeTwo<>(type, "min_price", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case INCREASE: {
                try {
                    Boolean castValue1 = Boolean.parseBoolean(value1);
                    Boolean castValue2 = Boolean.parseBoolean(value2);
                    return new ElementAttributeTwo<>(type, "increase", castValue1, castValue2);
                }
                catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + value1 + " или значение " + value2 + "\" имеет parse данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeOne<>(type, null, null);
                }
            }
            case USE: {
                try {
                    Boolean castValue1 = Boolean.parseBoolean(value1);
                    Boolean castValue2 = Boolean.parseBoolean(value2);
                    return new ElementAttributeTwo<>(type, "use", castValue1, castValue2);
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
            case DEFAULT_PRICE: {
                try {
                    List<Float> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Float castValue = Float.parseFloat(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "defaultPrice", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case PRICE: {
                try {
                    List<Float> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Float castValue = Float.parseFloat(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "price", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case SALES: {
                try {
                    List<Float> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Float castValue = Float.parseFloat(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "sales", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case MAX_PRICE: {
                try {
                    List<Float> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Float castValue = Float.parseFloat(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "max_price", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case MIN_PRICE: {
                try {
                    List<Float> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Float castValue = Float.parseFloat(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "min_price", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case INCREASE: {
                try {
                    List<Boolean> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Boolean castValue = Boolean.parseBoolean(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "increase", listAttribute);
                } catch (NumberFormatException ex) {
                    logger.warn("Значение \"" + values + "\" имеет неверный тип данных " +
                            "для запроса поиска по \"" + field + "\"");
                    return new ElementAttributeList<>(AttributeType.NOTHING.getID(), null, null);
                }
            }
            case USE: {
                try {
                    List<Boolean> listAttribute = new ArrayList<>();
                    for (String value : values) {
                        Boolean castValue = Boolean.parseBoolean(value);
                        listAttribute.add(castValue);
                    }
                    return new ElementAttributeList<>(type, "use", listAttribute);
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
