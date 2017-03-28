package org.hackathon.eatsmart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by avish on 3/28/2017.
 */

public class AdapterDishList extends ArrayAdapter<Dish> {
    private ArrayList<Dish> dishList;
    private static LayoutInflater inflater = null;

    public AdapterDishList (Activity activity, int textViewResourceId, ArrayList<Dish> dishList) {
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
        public TextView display_name;
        //public TextView display_number;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.menu_item, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.display_name);
                //holder.display_number = (TextView) vi.findViewById(R.id.display_number);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_name.setText(dishList.get(position).getDishName());
            //holder.display_number.setText(lDish.get(position).number);


        } catch (Exception e) {


        }
        return vi;
    }
}
