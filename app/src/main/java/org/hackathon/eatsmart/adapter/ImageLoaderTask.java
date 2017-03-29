package org.hackathon.eatsmart.adapter;

import android.os.AsyncTask;

import org.hackathon.eatsmart.ImageLoader;

/**
 * Created by menashee on 29/03/2017.
 */

public class ImageLoaderTask extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... params) {
        ImageLoader.loadImages();
        return null;
    }
}
