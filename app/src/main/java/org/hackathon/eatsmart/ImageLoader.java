package org.hackathon.eatsmart;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import org.hackathon.eatsmart.data.Dish;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menashee on 29/03/2017.
 */

public class ImageLoader {

    private static Map<String, Map<String, Drawable>> restaurantDishImageMap = new HashMap<>();

    public static void loadImages() {

        for (String restaurantName : Storage.getInstance().getRestaurantNames()) {
            HashMap<String, Drawable> dishImageMap = new HashMap<>();
            restaurantDishImageMap.put(restaurantName, dishImageMap);
            for (Dish dish : Storage.getInstance().getRestaurantDishes(restaurantName)) {
                dishImageMap.put(dish.getDishName(), drawableFromUrl(dish.getImageUrl()));
            }
        }
    }

    public static Drawable getDishImage(String restaurantName, String dishName) {
        return restaurantDishImageMap.get(restaurantName).get(dishName);
    }

    private static Drawable drawableFromUrl(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("User-agent","Mozilla/4.0");
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();

//        Bitmap bitmap = BitmapFactory.decodeStream(input);
            Drawable drawable = BitmapDrawable.createFromStream(input, "");
            return drawable;
        } catch (IOException e) {
            return null;
        }
    }
}
