package org.hackathon.eatsmart;

/**
 * Created by menashee on 29/03/2017.
 */

public interface JsonConstants {

    interface Restaurant {
        String RESTAURANTS = "restaurants";
        String NAME = "name";
        String STREET = "street";
        String STREET_NUM = "street_num";
        String CITY = "city";
        String TYPE = "type";
        String COUNTRY = "country";
        String LAT = "lat";
        String LNG = "lng";
    }

    interface Dish {
        String NAME = "name";
        String DESCRIPTION = "description";
        String PIC = "pic";
        String HEALTH_ATTRIBUTES = "healthAttrs";
        String RESTRICTIONS = "restrictions";
        String NUTRITIONAL_VALUES = "nutritions";
        String INGREDIENTS = "ingredients";
    }

    interface Ingredient {
        String NAME = "name";
        String QUANTITY = "quantity";
    }
}
