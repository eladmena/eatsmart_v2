package org.hackathon.eatsmart;

/**
 * Created by htg1ue on 3/28/2017.
 */

public enum eRestaurantType {
    STREET_FOOD("Street food"),
    BISTRO("Bistro"),
    SALAD_BAR("Salad bar"),
    COFFEE("Coffee"),
    HUMUS("Humus"),
    ITALIAN("Italian"),
    DAIRY("Dairy"),
    HUMBURGERS("Humburgers"),
    MEXICAN("Mexican"),
    PIZZA("Pizza"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    FAST_FOOD("Fast food");

    private String friendlyName;

    private eRestaurantType(String friendlyName){
        this.friendlyName = friendlyName;
    }

    @Override public String toString(){
        return friendlyName;
    }
}
