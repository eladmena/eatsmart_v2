package org.hackathon.eatsmart.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.adapter.NutritionAdapter;
import org.hackathon.eatsmart.data.Ingredient;
import org.hackathon.eatsmart.external.nutritionix.NutritionixFoodResponse;
import org.hackathon.eatsmart.external.nutritionix.NutritionixRestClient;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EditDishActivity extends AppCompatActivity {
    public static List<Ingredient> allIngredients = new ArrayList<>();
    private NutritionixRestClient client = new NutritionixRestClient();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);
        fillNutEmpty();
        Button btn1 = (Button) findViewById(R.id.ingButton);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IngredientListActivity.class);
                startActivity(intent);
            }
        });


        //Adding photo
        Button takePictureButton = (Button) findViewById(R.id.addPhoto);

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });


        //ImageView imageView = (ImageView) findViewById(R.id.imageview);

        //if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        //  takePictureButton.setEnabled(false);
        // ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);


        // }


    }

    @Override
    protected void onResume() {
        super.onResume();
        fillNutEmpty();

        //String ingredients = StringUtils.collectionToCommaDelimitedString(Arrays.asList(IngredientListActivity.ingredients));
        //updateNutrients(ingredients);
    }

    private void updateNutrients(String ingredients) {

    }

    private void fillNutEmpty() {
        String ingredients = StringUtils.collectionToCommaDelimitedString(allIngredients);
        NutritionixFoodResponse response = client.getNutrientsMock(ingredients, 1);

        String nutrition[] = {"Serving Weight Grams: " + response.getServing_weight_grams(),
                "Calories: " + response.getNf_calories(),
                "Total Fat: " + response.getNf_total_fat(),
                "Saturated fat: " + response.getNf_saturated_fat(),
                "Cholesterol: " + response.getNf_cholesterol(),
                "Sodium: " + response.getNf_sodium(),
                "Total Carbohydrate: " + response.getNf_cholesterol(),
                "Dietary Fiber: " + response.getNf_dietary_fiber(),
                "Sugars: " + response.getNf_sugars(),
                "Protein: " + response.getNf_protein(),
                "Potassium: " + response.getNf_potassium()};
        ArrayList<String> nut = new ArrayList<String>();
        for(int j=0; j< nutrition.length; j++)
        {
            nut.add(nutrition[j]);
        }
        NutritionAdapter nutAdapter = new NutritionAdapter(this,0, nut);
        ListView nutList = (ListView) findViewById(R.id.list_edit_dish_nut);
        nutList.setAdapter(nutAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);

                // File newfile  = getOutputMediaFile();
                //Uri uri = Uri.fromFile(newfile);
                // Uri contactUri = data.getData();
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contactUri);
                // imageView.setImageURI(contactUri);
            }
        }
    }

    /** Create a File for saving the image */
    private static File getOutputMediaFile() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;

    }





}
