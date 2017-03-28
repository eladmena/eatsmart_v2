package org.hackathon.eatsmart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.adapter.IngredientListAdapter;
import org.hackathon.eatsmart.data.Ingredient;

import java.util.ArrayList;

public class IngredientListActivity extends AppCompatActivity {

    private static ListView listview;
    private static Ingredient[] ingredients = new Ingredient[] {

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_ing_list);

        listview = (ListView) findViewById(R.id.MenuDishList);
        listView();

        /** Defining a click event listener for the button "Add" */
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                String input = edit.getText().toString();
                String[] animalsArray = input.split(",");
                if(animalsArray.length > 1)
                {
                    String ingName = animalsArray[0];
                    String quantity = animalsArray[1];
                    Ingredient newIngredient = new Ingredient( ingName ,quantity);
                    addToListView(newIngredient);
                    IngredientListAdapter adbDish = (IngredientListAdapter)listview.getAdapter();
                    adbDish.notifyDataSetChanged();
                }

            }
        };

        /** Defining a click event listener for the button "Delete" */
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                IngredientListAdapter dishAdapter = (IngredientListAdapter)listview.getAdapter();
                SparseBooleanArray checkedItemPositions = listview.getCheckedItemPositions();
                int itemCount = listview.getCount();


                for(int i=itemCount-1; i >= 0; i--){
                    int size = checkedItemPositions.size();
                    String s = String.valueOf(size);
                    Ingredient tmp = new Ingredient("fff" , s) ;
                    addToListView(tmp);
                    if(size > 0){
                        dishAdapter.removeFromList(i);

                    }
                }
                checkedItemPositions.clear();
                dishAdapter.notifyDataSetChanged();
            }
        };




        /** Setting the event listener for the add button */
        /** Reference to the add button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(listener);

        Button btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(listenerDel);

    }

    public void listView() {
        IngredientListAdapter adbDish;
        final ArrayList<Ingredient> myListItems = new ArrayList<Ingredient>();

        for (int i = 0; i < ingredients.length; ++i) {
            myListItems.add(ingredients[i]);
        }

        adbDish = new IngredientListAdapter(IngredientListActivity.this, 0, myListItems);
        listview.setAdapter(adbDish);
    }

    public void addToListView(Ingredient ingredient) {

        IngredientListAdapter dishAdapter = (IngredientListAdapter)listview.getAdapter();
        dishAdapter.addIngredient(ingredient);

    }






}



