package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.hackathon.eatsmart.R;
import org.hackathon.eatsmart.data.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by htg1ue on 3/28/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private static LayoutInflater inflater = null;

    private Activity _context;
    private ArrayList<Dish> dishList;
    // header titles
    // child data in format of header title, child title
    private HashMap<Dish, List<String>> _listDataChild;

    public ExpandableListAdapter(Activity context, ArrayList<Dish> listDataHeader,
                                 HashMap<Dish, List<String>> listChildData) {
        this._context = context;
        this.dishList = listDataHeader;
        this._listDataChild = listChildData;
        try {
            this.dishList = dishList;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this.dishList.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_dish_nutritions_data, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(this.dishList.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.dishList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.dishList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View vi = convertView;
        final ExpandableListAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.fragment_dishitem, null);
                holder = new ExpandableListAdapter.ViewHolder();

                holder.dish_name = (TextView) vi.findViewById(R.id.dishName);
                holder.dish_description = (TextView) vi.findViewById(R.id.dishDescription);
                holder.dish_image = (ImageView) vi.findViewById(R.id.dishImage);

                vi.setTag(holder);
            } else {
                holder = (ExpandableListAdapter.ViewHolder) vi.getTag();
            }

            Dish dish = dishList.get(groupPosition);
            holder.dish_name.setText(dish.getDishName());
            holder.dish_description.setText(dish.getDishDescription());
            String imageUrl = dish.getImageUrl();
            if (imageUrl != null) {
                AsyncTask<String, Void, Drawable> task = new ImageLoaderTask().execute(dish.getImageUrl());
                Drawable drawable = task.get();
                if (drawable != null) {
                    holder.dish_image.setImageDrawable(drawable);
                }
            }
        } catch (Exception e) {


        }
        return vi;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class ViewHolder {
        public TextView dish_name;
        public TextView dish_description;
        public ImageView dish_image;
    }
}