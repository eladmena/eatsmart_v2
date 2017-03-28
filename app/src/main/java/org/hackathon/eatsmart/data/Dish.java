package org.hackathon.eatsmart.data;

/**
 * Created by avish on 3/28/2017.
 */

public class Dish {
    private String dishName;
    private String dishDescription;
    private String imageurl;

    public Dish(String dishName, String dishDescription){
        this.dishName = dishName;
        this.dishDescription = dishDescription;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }
}
