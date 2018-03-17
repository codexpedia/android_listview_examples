package com.example.dynamiclistview.multipl_views_in_list_view_item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class MultiViewsItemAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private int listItemLayout;
    private ArrayList<Item> itemList;
    private Context context;

    public MultiViewsItemAdapter(Context context, int layoutId, ArrayList<Item> itemList) {
        this.context = context;
        this.listItemLayout = layoutId;
        this.itemList = itemList;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Item item = itemList.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.more = (TextView) convertView.findViewById(R.id.poi_list_item_more_options);
            convertView.setTag(viewHolder); // view lookup cache stored in tag
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList == null ? null : itemList.get(i);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    // The ViewHolder, only one item for simplicity and demonstration purposes, you can put all the views inside a row of the list into this ViewHolder
    private static class ViewHolder {
        TextView more;
    }

}