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
 * Created by htg1ue on 3/29/2017.
 */

public class NutritionAdapter extends ArrayAdapter<String> {
    private ArrayList<String> nutList;
    private static LayoutInflater inflater = null;

    public NutritionAdapter(Activity activity, int textViewResourceId, ArrayList<String> nutList) {
        super(activity, textViewResourceId, nutList);
        try {
            this.nutList = nutList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }
    public int getCount() {
        return nutList.size();
    }

    public String getItem(String position) {
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
        final DishAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.fragment_dish_nutritions_data, null);
                holder = new DishAdapter.ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.lblListItem);
                //holder.display_number = (TextView) vi.findViewById(R.id.display_number);


                vi.setTag(holder);
            } else {
                holder = (DishAdapter.ViewHolder) vi.getTag();
            }



            holder.display_name.setText(nutList.get(position));
            //holder.display_number.setText(lDish.get(position).number);


        } catch (Exception e) {


        }
        return vi;
    }

}
