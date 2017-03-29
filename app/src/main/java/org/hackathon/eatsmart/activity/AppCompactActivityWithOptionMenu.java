package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.hackathon.eatsmart.R;

import java.util.Map;

/**
 * Created by dlaettner on 29/03/2017.
 */

public class AppCompactActivityWithOptionMenu extends AppCompatActivity {

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
                startActivityForResult(menuIntent,1002);
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
