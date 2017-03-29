package org.hackathon.eatsmart;

import android.util.Log;

import com.jayway.jsonpath.JsonPath;

import org.hackathon.eatsmart.data.Dish;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;


//Example for how to add new dish
        /*JSONObject newDish = new JSONObject();
        try {
            newDish.put(JsonConstants.Dish.NAME, "Landver new Salad");

            JSONArray ingrids = new JSONArray();
            JSONObject ingrid = new JSONObject();
            ingrid.put(JsonConstants.Ingredient.NAME, "Batata");
            ingrid.put(JsonConstants.Ingredient.QUANTITY, "200g");
            ingrids.put(ingrid);
            newDish.put("ingredients", ingrids);

            JSONObject nuts = new JSONObject();
            nuts.put("calories", 500);
            newDish.put("nutritions", nuts);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Storage.getInstance().addDishToLandver(newDish);
        Log.d("json:", Storage.getInstance().getLandverRest().toString());
        try {
            Toast.makeText(getApplicationContext(), "storage json: " + Storage.getInstance().getLandverRest().get(JsonConstants.Restaurant.NAME), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

/**
 * Created by tsadok on 27/03/2017.
 */
public class Storage {
    private static Storage mInstance = null;
    private JSONObject jObj;

    private Storage() {
        try {
            jObj = new JSONObject(loadJsonFromResources());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
    }

    public static Storage getInstance() {
        if (mInstance == null) {
            mInstance = new Storage();
        }
        return mInstance;
    }

    public JSONObject getJson() {
        return this.jObj;
    }

    public void setJson(JSONObject jObj) {
        this.jObj = jObj;
    }

    public JSONObject getLandverRest() {
        try {
            JSONArray rests = jObj.getJSONArray(JsonConstants.Restaurant.RESTAURANTS);

            for (int i = 0; i < rests.length(); i++) {
                JSONObject rest = rests.getJSONObject(i);
                if (rest.getString(JsonConstants.Restaurant.NAME).equals("Landver")) {
                    return rest;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONArray getLandverDishes() {
        JSONObject landverRest = getLandverRest();
        try {
            return landverRest.getJSONArray("dishes");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addDishToLandver(JSONObject newDish) {
        JSONObject landverRest = getLandverRest();
        try {
            JSONArray dishes = landverRest.getJSONArray("dishes");
            dishes.put(newDish);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Dish> getRestaurantDishes(String restName) {
        net.minidev.json.JSONArray dishesJsonArray = JsonPath.read(jObj.toString(), "$.restaurants[?(@.name == '" + restName + "')].dishes");

        ArrayList<Dish> dishList = new ArrayList<>();
        dishesJsonArray = (net.minidev.json.JSONArray) dishesJsonArray.get(0); // getInnerArray
        for (int i = 0; i < dishesJsonArray.size(); i++) {
            LinkedHashMap<String, Object> dishMap = (LinkedHashMap<String, Object>) dishesJsonArray.get(i);
            String name = (String) dishMap.get(JsonConstants.Dish.NAME);
            String description = (String) dishMap.get(JsonConstants.Dish.DESCRIPTION);
            String picUrl = (String) dishMap.get(JsonConstants.Dish.PIC);
            net.minidev.json.JSONArray healthAttrArray = (net.minidev.json.JSONArray)dishMap.get(JsonConstants.Dish.HEALTH_ATTRIBUTES);
            Set<String> healthAttributeSet = new HashSet<>(healthAttrArray.size());
            for (int j = 0; j < healthAttrArray.size(); j++) {
                healthAttributeSet.add((String)healthAttrArray.get(0));
            }
            dishList.add(new Dish(name, description, picUrl, healthAttributeSet));
        }

        return dishList;
    }

    private String loadJsonFromResources() {
        InputStream is = MyApplication.getMyResources().openRawResource(org.hackathon.eatsmart.R.raw.rests);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        return jsonString;
    }
}