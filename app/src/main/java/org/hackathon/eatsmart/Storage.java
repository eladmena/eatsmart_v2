package org.hackathon.eatsmart;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tsadok on 27/03/2017.
 */
public class Storage {
    private static Storage mInstance = null;
    private String json = "{\n  \"restaurants\": [\n    {\n      \"name\": \"Landver\",\n      \"addr\": \"Airport city, Lod\",\n      \"lat\": 31.98610904283177,\n      \"lng\": 34.91290658712387,\n      \"dishes\": [\n        {\n          \"name\": \"Chicken Tika\",\n          \"description\": \"An Indian Dish With Carri\",\n          \"pic\": \"http://dishpics/pic/123211\",\n          \"ingredients\": [\n            {\n              \"name\": \"carri\",\n              \"weight\": \"1 spoon\"\n            },\n            {\n              \"name\": \"chicken breast\",\n              \"weight\": \"4\"\n            },\n            {\n              \"name\": \"rise\",\n              \"weight\": \"250g\"\n            }\n          ],\n          \"nutritions\": [\n            {\n              \"serving_weight_grams\": 204.525,\n              \"calories\": 305.43,\n              \"total_fat\": 18.78,\n              \"saturated_fat\": 7.33,\n              \"cholesterol\": 95.99,\n              \"sodium\": 270.88,\n              \"total_carbohydrate\": 5.05,\n              \"dietary_fiber\": 2.11,\n              \"sugars\": 1.71,\n              \"protein\": 29.11,\n              \"potassium\": 501.03\n            }\n          ]\n        }\n      ]\n    }\n  ]\n}";
    private JSONObject jObj;

    private Storage(){
        try {
            jObj = new JSONObject(json);
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
}
