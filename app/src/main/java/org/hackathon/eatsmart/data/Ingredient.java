package org.hackathon.eatsmart.data;


/**
 * Created by Shaked on 3/28/2017.
 */

public class Ingredient {
    public String item_name;
    public String item_quantity;


    public Ingredient(String name, String quantity ){
        item_name = name;
        item_quantity = quantity;
    }

    @Override
    public String toString() {
        return item_quantity + " " + item_name;
    }
}