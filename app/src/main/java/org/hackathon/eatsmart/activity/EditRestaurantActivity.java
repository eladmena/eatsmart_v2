package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.hackathon.eatsmart.Storage;
import org.json.JSONException;
import org.json.JSONObject;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.RestaurantType;

public class EditRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initRestData();

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

        mySpinner.setAdapter(new ArrayAdapter<RestaurantType>(this, android.R.layout.simple_spinner_item, RestaurantType.values()));
        mySpinner.setSelection(3, true);
    }

    private void initRestData()
    {
        JSONObject rest = Storage.getInstance().getLandverRest();
        try {
            SetText((TextView)findViewById(R.id.cityRestName),rest.getString("city"));
            SetText((TextView)findViewById(R.id.restName),rest.getString("name"));
            SetText((TextView)findViewById(R.id.streetNumberRestName),rest.getString("street_num"));
            SetText((TextView)findViewById(R.id.streetRestName),rest.getString("street"));
            setSpinnerSelection(rest.getString("type"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void SetText(TextView textView, String str)
    {
        int start = Math.max(textView.getSelectionStart(), 0);
        int end = Math.max(textView.getSelectionEnd(), 0);
        textView.setText(str.toCharArray(),0, str.length());
    }

    private void setSpinnerSelection(String selected)
    {
        int i = 0;
        Spinner mySpinner = (Spinner) findViewById(R.id.restTypes);

        for (RestaurantType rest : RestaurantType.values()) {
            if(rest.toString().equals(selected))
            {
                mySpinner.setAdapter(new ArrayAdapter<RestaurantType>(this, android.R.layout.simple_spinner_item, RestaurantType.values()));
                mySpinner.setSelection(i, true);
                break;
            }
            i++;
        }
    }
}
