package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.hackathon.eatsmart.JsonConstants;
import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;
import org.hackathon.eatsmart.adapter.DishAdapter;
import org.hackathon.eatsmart.data.Dish;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EditMenuActivity extends AppCompatActivity {

    Storage AppStorage = Storage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), EditDishActivity.class);
                startActivity(menuIntent);
            }
        });

        FloatingActionButton fab_done = (FloatingActionButton) findViewById(R.id.fab_done);
        fab_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menuIntent);
            }
        });

        final ArrayList<Dish> myListItems = new ArrayList<>();

//        Collections.addAll(myListItems, DISHES);
        JSONArray jDishes = AppStorage.getLandverDishes();
        if (jDishes != null) {
            for (int i = 0; i < jDishes.length(); i++) {
                try {
                    JSONObject jDish = jDishes.getJSONObject(i);
                    String dishName = jDish.getString(JsonConstants.Dish.NAME);
                    String dishDesc = jDish.getString(JsonConstants.Dish.DESCRIPTION);
                    myListItems.add(new Dish (dishName, dishDesc));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        final DishAdapter adbDish = new DishAdapter (EditMenuActivity.this, 0, myListItems);

        final ListView listview = (ListView) findViewById(R.id.MenuDishList);
        listview.setAdapter(adbDish);
        listview.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listview.setStackFromBottom(false);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final Dish item = (Dish) parent.getItemAtPosition(position);
                view.animate().setDuration(200).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                //myListItems.remove(item);
                                adbDish.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });

    }

}
