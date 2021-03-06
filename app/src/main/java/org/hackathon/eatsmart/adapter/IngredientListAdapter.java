package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.Ingredient;

import java.util.ArrayList;

/**
 * Created by avish on 3/26/2017.
 */

public class IngredientListAdapter extends ArrayAdapter<Ingredient> {
    private ArrayList<Ingredient> ingredientList;
    private static LayoutInflater inflater = null;

    public IngredientListAdapter(Activity activity, int textViewResourceId, ArrayList<Ingredient> ingredientList) {
        super(activity, textViewResourceId, ingredientList);
        try {
            this.ingredientList = ingredientList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return ingredientList.size();
    }

    public Ingredient getItem(Ingredient position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public void removeFromList(int i) {
        ingredientList.remove(i);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_quantity;
        public CheckBox checkbox;

    }



    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.dish_item, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.display_name);
                holder.display_quantity = (TextView) vi.findViewById(R.id.display_quantity);
                holder.checkbox = (CheckBox) vi.findViewById(R.id.checkBox1);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_name.setText(ingredientList.get(position).item_name);
            holder.display_quantity.setText(ingredientList.get(position).item_quantity);


        } catch (Exception e) {


        }
        return vi;
    }
}