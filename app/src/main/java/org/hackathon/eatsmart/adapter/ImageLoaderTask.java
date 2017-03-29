package org.hackathon.eatsmart.adapter;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by menashee on 29/03/2017.
 */

public class ImageLoaderTask extends AsyncTask<String, Void, Drawable> {


    @Override
    protected Drawable doInBackground(String... params) {
        try {
            return drawableFromUrl(params[0]);
        } catch (IOException e) {
//            Resources res = getResources();
//            Drawable drawable = res.getDrawable(R.drawable.no_image);
//            BitmapFactory.
////            BitmapFactory.decodeResource(R, R.mipmap.no_image);
////            return Bitmap.createBitmap()
            return null; // default image
        }
    }

    private static Drawable drawableFromUrl(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();

//        Bitmap bitmap = BitmapFactory.decodeStream(input);
        Drawable drawable = BitmapDrawable.createFromStream(input, "");
        return drawable;
    }
}
