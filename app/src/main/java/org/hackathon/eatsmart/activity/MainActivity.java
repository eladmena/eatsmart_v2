package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.hackathon.eatsmart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R.drawable.mainover1.setAlpha(30);
        //View v = this.findViewById(this->get)

        Button cutomerButton = (Button) findViewById(R.id.customerBtn);
        cutomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), CustomerRestaurantChooserActivity.class);
                startActivity(menuIntent);
            }
        });

        Button restaurantButton = (Button) findViewById(R.id.restaurantBtn);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), RestaurantMainActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
