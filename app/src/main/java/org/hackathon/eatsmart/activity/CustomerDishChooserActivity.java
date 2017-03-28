package org.hackathon.eatsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

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
            myListItems.add(new Dish(
                    "Dish" + prefix + i,
                    "Description " + prefix + i + "\nIt's a bitch to read all this long long text, like didn't you hear of lorem ipsum and all of that?!?",
                    "http://assets.epicurious.com/photos/54af56b3c4a891cc44cceb29/master/pass/51171400_chicken-tikka-masala_1x1.jpg")
            );
        }

        final ExtendedDishAdapter dishAdapter = new ExtendedDishAdapter(this, 0, myListItems);

        dishList.setAdapter(dishAdapter);
        dishList.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        dishList.setStackFromBottom(false);

        String restName = getIntent().getStringExtra("restName");
        Toast.makeText(getApplicationContext(), restName, Toast.LENGTH_LONG).show();
    }
}
