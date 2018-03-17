package com.example.dynamiclistview.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private int listItemLayout;
    private ItemListener itemListener;

    public ItemArrayAdapter(Context context, int layoutId, ArrayList<Item> itemList) {
        super(context, layoutId, itemList);
        listItemLayout = layoutId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Item item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(listItemLayout, parent, false);
            viewHolder.item = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.toggle = (TextView) convertView.findViewById(R.id.tv_toggle);
            convertView.setTag(viewHolder); // view lookup cache stored in tag
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        // Populate the data into the template view using the data object
        viewHolder.item.setText(item.getName());
        if (item.isExpandable()) {
            viewHolder.toggle.setVisibility(View.VISIBLE);
            viewHolder.toggle.setText(item.isExpanded() ? "-" : "+");
        } else {
            viewHolder.toggle.setVisibility(View.INVISIBLE);
        }

        viewHolder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean expand;
                if (item.isExpanded()) {
                    expand = false;
                    item.setExpanded(false);
                } else {
                    expand = true;
                    item.setExpanded(true);
                }
                itemListener.onToggle(position, expand);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }

    // The ViewHolder, only one item for simplicity and demonstration purposes, you can put all the views inside a row of the list into this ViewHolder
    private static class ViewHolder {
        TextView item, toggle;
    }

    public interface ItemListener {
        void onToggle(int pos, boolean expand);
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}