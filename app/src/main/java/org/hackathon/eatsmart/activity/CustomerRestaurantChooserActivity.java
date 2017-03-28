package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.Storage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CustomerRestaurantChooserActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(getApplicationContext(), CustomerDishChooserActivity.class);
                startActivity(menuIntent);

            }
        }); */
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        JSONObject json =  Storage.getInstance().getJson();

        JSONArray rests = null;
        try {
            rests = json.getJSONArray("restaurants");
            for(int i = 0; i < rests.length(); i++) {
                JSONObject rest = rests.getJSONObject(i);
                LatLng loc = new LatLng(rest.getDouble("lat"), rest.getDouble("lng"));
                mMap.addMarker(new MarkerOptions().position(loc).title(rest.getString("name")));
                moveToCurrentLocation(loc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Toast.makeText(getApplicationContext(), "Clicked a window with title..." + marker.getTitle(), Toast.LENGTH_SHORT).show();
                Intent menuIntent = new Intent(getApplicationContext(), CustomerDishChooserActivity.class);
                startActivity(menuIntent);
                return true;
            }
        });
    }

    private void moveToCurrentLocation(LatLng currentLocation)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


    }


}
