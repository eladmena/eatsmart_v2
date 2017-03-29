package org.hackathon.eatsmart.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;
import org.hackathon.eatsmart.adapter.ExpandableListAdapter;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;
import java.util.Arrays;
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
        filterDishes(restaurantDishes, restrictions);

        String nutrition[] = {"Serving weight grams: 204.525",
                "Calories: 305.43",
                "Total fat: 18.78",
                "Saturated fat: 7.33",
                "Cholesterol: 95.99",
                "Sodium: 270.88",
                "Total carbohydrate: 5.05",
                "Dietary fiber: 2.11",
                "Sugars: 1.71",
                "Protein: 29.11",
                "Potassium: 501.03"};

        final List<String> nutrientsArray = Arrays.asList(nutrition);
        final ExpandableListView expListView = (ExpandableListView) findViewById(R.id.custDishList);

        for (Dish restaurantDish : restaurantDishes) {
            listDataChild.put(restaurantDish, nutrientsArray);
        }

        //final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, myListItems);
        final ExpandableListAdapter dishAdapter = new ExpandableListAdapter(this, restaurantDishes, listDataChild);
        expListView.setAdapter(dishAdapter);

        //dishList.setAdapter(dishAdapter);
        //dishList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        //dishList.setStackFromBottom(false);

//        Toast.makeText(getApplicationContext(), restName, Toast.LENGTH_LONG).show();
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
