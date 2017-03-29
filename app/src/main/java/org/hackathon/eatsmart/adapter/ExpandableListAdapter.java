package org.hackathon.eatsmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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
        return this._listDataChild.get(this.dishList.get(groupPosition))
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

            holder.dish_name.setText(dishList.get(groupPosition).getDishName());
            holder.dish_description.setText(dishList.get(groupPosition).getDishDescription());
            String imageUrl = dishList.get(groupPosition).getImageUrl();
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

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}