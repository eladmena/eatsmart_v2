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

import org.hackathon.eatsmart.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditDishActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);

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
