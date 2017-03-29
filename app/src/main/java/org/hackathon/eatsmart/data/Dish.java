package org.hackathon.eatsmart.data;

import java.util.Collections;
import java.util.Set;

/**
 * Created by avish on 3/28/2017.
 */

public class Dish {
    private String dishName;
    private String dishDescription;
    private String imageUrl;
    private Set<String> restrictions;

    public Dish(String dishName, String dishDescription) {
        this(dishName, dishDescription, null, Collections.<String>emptySet());
    }

    public Dish(String dishName, String dishDescription, String imageUrl, Set<String> restrictions) {
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.imageUrl = imageUrl;
        this.restrictions = restrictions;
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

    public Set<String> getRestrictions() {
        return Collections.unmodifiableSet(restrictions);
    }
}
