package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.Dish;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by menashee on 28/03/2017.
 */

public class ExtendedDishAdapter extends ArrayAdapter<Dish> {
    private ArrayList<Dish> dishList;
    private static LayoutInflater inflater = null;

    public ExtendedDishAdapter(Activity activity, int textViewResourceId, ArrayList<Dish> dishList) {
        super(activity, textViewResourceId, dishList);
        try {
            this.dishList = dishList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return dishList.size();
    }

    public Dish getItem(Dish position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView dish_name;
        public TextView dish_description;
        public ImageView dish_image;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ExtendedDishAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.fragment_dishitem, null);
                holder = new ExtendedDishAdapter.ViewHolder();

                holder.dish_name = (TextView) vi.findViewById(R.id.dishName);
                holder.dish_description = (TextView) vi.findViewById(R.id.dishDescription);
                holder.dish_image = (ImageView) vi.findViewById(R.id.dishImage);

                vi.setTag(holder);
            } else {
                holder = (ExtendedDishAdapter.ViewHolder) vi.getTag();
            }

            holder.dish_name.setText(dishList.get(position).getDishName());
            holder.dish_description.setText(dishList.get(position).getDishDescription());
            String imageUrl = dishList.get(position).getImageUrl();
            if (imageUrl != null) {
                try {
//                    holder.dish_image.setImageDrawable(drawableFromUrl(imageUrl));
//                    holder.dish_image.setImageBitmap(bitmapFromUrl(imageUrl));
//                    holder.dish_image.setImageURI(Uri.parse(imageUrl));
//                    holder.dish_image.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
                } catch (Exception e) {
                    // do nothing, will not override default image
                    holder.dish_name.setText(e.getMessage());
                }
            }
        } catch (Exception e) {


        }
        return vi;
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }

    private static Bitmap bitmapFromUrl(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();

        return BitmapFactory.decodeStream(input);
    }


}
