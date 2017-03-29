package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;

/**
 * Created by menashee on 28/03/2017.
 */

public class ExtendedDishAdapter extends ArrayAdapter<Dish> {
    private ArrayList<Dish> dishList;
    private ArrayList<AsyncTask<String, Void, Drawable>> tasks;
    private static LayoutInflater inflater = null;

    public ExtendedDishAdapter(Activity activity, int textViewResourceId, ArrayList<Dish> dishList) {
        super(activity, textViewResourceId, dishList);
        try {
            this.dishList = dishList;
            tasks = new ArrayList<>(dishList.size());

            for (Dish dish : dishList) {
                tasks.add(new ImageLoaderTask().execute(dish.getImageUrl()));
            }

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
//            if (imageUrl != null) {
//                Drawable drawable = tasks.get(position).get();
//                if (drawable != null) {
//                    holder.dish_image.setImageDrawable(drawable);
//                }
//            }
        } catch (Exception e) {
        }
        return vi;
    }
}
