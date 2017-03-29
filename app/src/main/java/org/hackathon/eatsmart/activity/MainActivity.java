package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this,R.xml.food_filtering,false);

        //R.drawable.mainover1.setAlpha(30);
        //View v = this.findViewById(this->get)

        Button cutomerButton = (Button) findViewById(R.id.customerBtn);
        cutomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), CustomerRestaurantChooserActivity.class);
                startActivity(menuIntent);
            }
        });

        Button restaurantButton = (Button) findViewById(R.id.restaurantBtn);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), EditRestaurantActivity.class);
                startActivity(menuIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filtering_menu_item:
                Intent menuIntent = new Intent(this, FoodFilteringActivity.class);
                startActivity(menuIntent);
                return true;
            case R.id.about_menu_item:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                Map<String, ?> keys = prefs.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Log.d("map values",entry.getKey() + ": " +
                            entry.getValue().toString());
                }
                return true;
            default:
                return true;
        }
    }
}
