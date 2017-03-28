package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.Dish;

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
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ExtendedDishAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.menu_item, null);
                holder = new ExtendedDishAdapter.ViewHolder();

                holder.dish_name = (TextView) vi.findViewById(R.id.dishName);
                holder.dish_description = (TextView) vi.findViewById(R.id.dishDescription);

                vi.setTag(holder);
            } else {
                holder = (ExtendedDishAdapter.ViewHolder) vi.getTag();
            }

            holder.dish_name.setText(dishList.get(position).getDishName());
            holder.dish_description.setText(dishList.get(position).getDishDescription());
        } catch (Exception e) {


        }
        return vi;
    }
}
