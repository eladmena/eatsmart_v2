package org.hackathon.eatsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;
import org.hackathon.eatsmart.adapter.ExtendedDishAdapter;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;

public class CustomerDishChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dish_chooser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String restName = getIntent().getStringExtra("restName");
        final ListView dishList = (ListView) findViewById(R.id.custDishList);

        ArrayList<Dish> restaurantDishes = Storage.getInstance().getRestaurantDishes(restName);

        final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, restaurantDishes);

        dishList.setAdapter(dishAdapter);
        dishList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        dishList.setStackFromBottom(false);

        Toast.makeText(getApplicationContext(), restName, Toast.LENGTH_LONG).show();
    }
}
