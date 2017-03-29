package org.hackathon.eatsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.adapter.ExpandableListAdapter;
import org.hackathon.eatsmart.Storage;
import org.hackathon.eatsmart.adapter.ExtendedDishAdapter;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerDishChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dish_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        HashMap<Dish, List<String>> listDataChild = new HashMap<Dish,List<String>>();


        String restName = getIntent().getStringExtra("restName");
        final ListView dishList = (ListView) findViewById(R.id.custDishList);

        ArrayList<Dish> restaurantDishes = Storage.getInstance().getRestaurantDishes(restName);

        final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, restaurantDishes);


       // final ListView dishList = (ListView) findViewById(R.id.custDishList);
        final ExpandableListView expListView = (ExpandableListView) findViewById(R.id.custDishList);
        final ArrayList<Dish> myListItems = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            String prefix = i < 10 ? "0" : "";
            Dish addedDish = new Dish(
                    "Dish" + prefix + i,
                    "Description " + prefix + i + "\nIt's a bitch to read all this long long text, like didn't you hear of lorem ipsum and all of that?!?",
                    "http://assets.epicurious.com/photos/54af56b3c4a891cc44cceb29/master/pass/51171400_chicken-tikka-masala_1x1.jpg");
            myListItems.add(addedDish);
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
            List<String> nut = new ArrayList<String>();
            for(int j=0; j< nutrition.length; j++)
            {
                nut.add(nutrition[j]);
            }
            listDataChild.put(addedDish,nut);

        }


        //final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, myListItems);
        final ExpandableListAdapter dishAdapter = new ExpandableListAdapter(this,myListItems,listDataChild);
expListView.setAdapter(dishAdapter);

        //dishList.setAdapter(dishAdapter);
        //dishList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        //dishList.setStackFromBottom(false);

        Toast.makeText(getApplicationContext(), restName, Toast.LENGTH_LONG).show();
    }
}
