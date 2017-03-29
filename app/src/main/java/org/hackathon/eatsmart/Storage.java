package org.hackathon.eatsmart;

import android.content.res.Resources;
import android.util.Log;

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


//Example for how to add new dish
        /*JSONObject newDish = new JSONObject();
        try {
            newDish.put("name", "Landver new Salad");

            JSONArray ingrids = new JSONArray();
            JSONObject ingrid = new JSONObject();
            ingrid.put("name", "Batata");
            ingrid.put("quantity", "200g");
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
            Toast.makeText(getApplicationContext(), "storage json: " + Storage.getInstance().getLandverRest().get("name"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

/**
 * Created by tsadok on 27/03/2017.
 */
public class Storage {
    private static Storage mInstance = null;
    private String json = "{\n  \"restaurants\": [\n    {\n      \"name\": \"Mono Greek\",\n\t  \"street\": \"Negev\",\n\t  \"street_num\": 2,\n\t  \"city\": \"Airport City\",\n\t  \"type\": \"Greek\",\n\t  \"country\": \"Israel\",\n      \"lat\": 31.985953206105055,\n      \"lng\": 34.912804663181305\n\t},\n    {\n      \"name\": \"Landver\",\n\t  \"street\": \"Negev\",\n\t  \"street_num\": 2,\n\t  \"city\": \"Airport City\",\n\t  \"type\": \"Coffee\",\n\t  \"country\": \"Israel\",\n      \"lat\": 31.98610904283177,\n      \"lng\": 34.91290658712387,\n      \"dishes\": [\n        {\n          \"name\": \"Chicken Tikka\",\n          \"description\": \"An Indian Dish With Carri\",\n          \"pic\": \"http://assets.epicurious.com/photos/54af56b3c4a891cc44cceb29/master/pass/51171400_chicken-tikka-masala_1x1.jpg\",\n\t\t  \"healthAttrs\": [\"High Protein\", \"No Gluten\"],\n          \"ingredients\": [\n            {\n              \"name\": \"carri\",\n              \"quantity\": \"1 spoon\"\n            },\n            {\n              \"name\": \"chicken breast\",\n              \"quantity\": \"4\"\n            },\n            {\n              \"name\": \"rise\",\n              \"quantity\": \"250g\"\n            }\n          ],\n          \"nutritions\":\n            {\n              \"serving_weight_grams\": 204.525,\n              \"calories\": 305.43,\n              \"total_fat\": 18.78,\n              \"saturated_fat\": 7.33,\n              \"cholesterol\": 95.99,\n              \"sodium\": 270.88,\n              \"total_carbohydrate\": 5.05,\n              \"dietary_fiber\": 2.11,\n              \"sugars\": 1.71,\n              \"protein\": 29.11,\n              \"potassium\": 501.03\n            }\n        },\n\t\t{\n          \"name\": \"Nourishing Muesli\",\n          \"description\": \"Pronounced as muse-lee, it is an uncooked mixture of nuts, seeds, grains, dried fruits, and spices\",\n          \"pic\": \"http://nutritionstripped.com/wp-content/uploads/2014/02/nourishing-muesli5-e1392592834715.jpg\",\n\t\t  \"healthAttrs\": [\"High Protein\", \"Good Fat\"],\n          \"ingredients\": [\n            {\n              \"name\": \"rolled oats\",\n              \"quantity\": \"2 cups\"\n            },\n            {\n              \"name\": \"quinoa flakes\",\n              \"quantity\": \"2 cups\"\n            },\n            {\n              \"name\": \"almonds\",\n              \"quantity\": \"1 cup\"\n            },\n\t\t\t{\n              \"name\": \"vanilla extract\",\n              \"quantity\": \"1 teaspoon\"\n            }\n          ],\n          \"nutritions\":\n            {\n              \"serving_weight_grams\": 71.06607,\n\t\t\t  \"calories\": 233.67,\n              \"total_fat\": 13.7,\n              \"saturated_fat\": 2.9,\n              \"cholesterol\": 0,\n              \"sodium\": 318.35,\n              \"total_carbohydrate\": 23.74,\n              \"dietary_fiber\": 4.72,\n              \"sugars\": 8.59,\n              \"protein\": 7.54,\n              \"potassium\": 359.24\n            }\n        }\n      ]\n    }\n  ]\n}\n";
    private JSONObject jObj;

    private Storage(){
        try {
            jObj = new JSONObject(loadJsonFromResources());
            //jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
    }

    public static Storage getInstance(){
        if(mInstance == null)
        {
            mInstance = new Storage();
        }
        return mInstance;
    }

    public JSONObject getJson(){
        return this.jObj;
    }

    public void setJson(JSONObject jObj){
        this.jObj = jObj;
    }

    public JSONObject getLandverRest() {
        try {
            JSONArray rests = jObj.getJSONArray("restaurants");

            for(int i = 0; i < rests.length(); i++) {
                JSONObject rest = rests.getJSONObject(i);
                if (rest.getString("name").equals("Landver")){
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