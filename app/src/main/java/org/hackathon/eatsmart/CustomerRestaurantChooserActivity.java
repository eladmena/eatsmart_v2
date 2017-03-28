package org.hackathon.eatsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerRestaurantChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_restaurant_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), CustomerDishChooserActivity.class);
                startActivity(menuIntent);

            }
        });
    }

}
