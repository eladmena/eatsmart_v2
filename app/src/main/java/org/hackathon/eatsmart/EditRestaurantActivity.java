package org.hackathon.eatsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initRestaurantTypes();
        Button fab = (Button) findViewById(R.id.applyBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), EditMenuActivity.class);
                startActivity(menuIntent);
            }});
    }
    private void initRestaurantTypes()
    {
        Spinner mySpinner = (Spinner) findViewById(R.id.restTypes);

        mySpinner.setAdapter(new ArrayAdapter<eRestaurantType>(this, android.R.layout.simple_spinner_item, eRestaurantType.values()));
    }
}
