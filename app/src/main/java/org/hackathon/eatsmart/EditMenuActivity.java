package org.hackathon.eatsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class EditMenuActivity extends AppCompatActivity {

    private static Dish[] DISHES = new Dish[] {
            new Dish ("A"),
            new Dish ("B"),
            new Dish ("C"),
            new Dish ("D"),
            new Dish ("E"),
            new Dish ("F"),
            new Dish ("G"),
            new Dish ("H"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("I"),
            new Dish ("End"),
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

        final ListView listview = (ListView) findViewById(R.id.MenuDishList);
        final ArrayList<Dish> myListItems = new ArrayList<>();
        Collections.addAll(myListItems, DISHES);
        final AdapterDishList adbDish = new AdapterDishList (EditMenuActivity.this, 0, myListItems);

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
                                myListItems.remove(item);
                                adbDish.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });

    }

}
