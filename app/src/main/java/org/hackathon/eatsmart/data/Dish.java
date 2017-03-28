package org.hackathon.eatsmart.data;

/**
 * Created by avish on 3/28/2017.
 */

public class Dish {
    private String dishName;
    private String dishDescription;
    private String imageUrl;

    public Dish(String dishName, String dishDescription) {
        this(dishName, dishDescription, null);
    }

    public Dish(String dishName, String dishDescription, String imageUrl) {
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.imageUrl = imageUrl;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
