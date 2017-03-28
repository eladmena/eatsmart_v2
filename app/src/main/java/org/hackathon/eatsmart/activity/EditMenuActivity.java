package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.hackathon.eatsmart.adapter.DishAdapter;
import org.hackathon.eatsmart.data.Dish;
import org.hackathon.eatsmart.R;

import java.util.ArrayList;
import java.util.Collections;

public class EditMenuActivity extends AppCompatActivity {

    Storage AppStorage = Storage.getInstance();

    private static Dish[] DISHES = new Dish[] {
            new Dish ("A", "aaa"),
            new Dish ("B", "bbb"),
            new Dish ("C", "ccc"),
            new Dish ("D", "ddd"),
            new Dish ("E", "eee"),
            new Dish ("F", "fff"),
            new Dish ("G", "ggg"),
            new Dish ("H", "hhh"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("I", "iii"),
            new Dish ("End", "end is the end"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), EditDishActivity.class);
                startActivity(menuIntent);
            }
        });

        final ArrayList<Dish> myListItems = new ArrayList<>();

//        Collections.addAll(myListItems, DISHES);
        JSONArray jDishes = AppStorage.getLandverDishes();
        if (jDishes != null) {
            for (int i = 0; i < jDishes.length(); i++) {
                String dishName = "";
                String dishDesc = "";
                try {
                    JSONObject jDish = jDishes.getJSONObject(i);
                    dishName = jDish.getString("name");
                    dishDesc = jDish.getString("description");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myListItems.add(new Dish (dishName, dishDesc));
            }
        }

        final AdapterDishList adbDish = new AdapterDishList (EditMenuActivity.this, 0, myListItems);

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
