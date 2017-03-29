package org.hackathon.eatsmart.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;
import org.hackathon.eatsmart.adapter.ExpandableListAdapter;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomerDishChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dish_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HashMap<Dish, List<String>> listDataChild = new HashMap<>();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> restrictions = new HashSet<>();
        Map<String, ?> allPreferences = prefs.getAll();
        for (String key : allPreferences.keySet()) {
            if (prefs.getBoolean(key, false)) {
                restrictions.add(key);
            }
        }

        String restName = getIntent().getStringExtra("restName");

        ArrayList<Dish> restaurantDishes = Storage.getInstance().getRestaurantDishes(restName);
        int totalRestDishes = restaurantDishes.size();
        filterDishes(restaurantDishes, restrictions);

        final ExpandableListView expListView = (ExpandableListView) findViewById(R.id.custDishList);

        for (Dish restaurantDish : restaurantDishes) {
            listDataChild.put(restaurantDish, new ArrayList<>(restaurantDish.getNutritionalValues()));
        }

        final ExpandableListAdapter dishAdapter = new ExpandableListAdapter(this, restaurantDishes, listDataChild, restName);
        expListView.setAdapter(dishAdapter);

        if (listDataChild.isEmpty()) {
            String message = totalRestDishes == 0 ? ("No dishes for " + restName) : "No dishes match user's health filter"; // TODO I18N
            message += ", try a different restaurant.";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void filterDishes(ArrayList<Dish> restaurantDishes, Set<String> restrictions) {

        for (Iterator<Dish> iterator = restaurantDishes.iterator(); iterator.hasNext(); ) {
            Dish dish = iterator.next();
            if (!dish.getRestrictions().containsAll(restrictions)) {
                iterator.remove();
            }
        }
    }
}
