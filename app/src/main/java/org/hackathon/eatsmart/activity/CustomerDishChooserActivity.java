package org.hackathon.eatsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.hackathon.eatsmart.R;
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

        final ListView dishList = (ListView) findViewById(R.id.custDishList);
        final ArrayList<Dish> myListItems = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            String prefix = i < 10 ? "0" : "";
            myListItems.add(new Dish("Dish" + prefix + i, "Description " + prefix + i));
        }

        final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, myListItems);

        dishList.setAdapter(dishAdapter);
        dishList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        dishList.setStackFromBottom(false);
    }

}
