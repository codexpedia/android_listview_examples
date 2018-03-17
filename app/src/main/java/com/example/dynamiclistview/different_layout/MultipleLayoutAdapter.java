package com.example.dynamiclistview.different_layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;
import com.example.dynamiclistview.nested.ExpandableView;

import java.util.ArrayList;

public class MultipleLayoutAdapter extends ArrayAdapter<Item> {

    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_ONE = 0;
    private static final int VIEW_TYPE_TWO = 1;

    private ItemListener itemListener;


    public MultipleLayoutAdapter(Context context, ArrayList<Item> itemList) {
        super(context, R.layout.different_item_layout_type_one, itemList);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Item item = getItem(position);
        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_ONE:
                ViewHolderSimple viewHolderSimple;
                if (convertView == null) {
                    viewHolderSimple = new ViewHolderSimple();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.different_item_layout_type_one, parent, false);
                    viewHolderSimple.item = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(viewHolderSimple); // view lookup cache stored in tag
                } else {
                    viewHolderSimple = (ViewHolderSimple) convertView.getTag();
                }
                viewHolderSimple.item.setText(item.getName());
                break;
            case VIEW_TYPE_TWO:
                ViewHolderExpandable viewHolderExpandable;
                if (convertView == null) {
                    viewHolderExpandable = new ViewHolderExpandable();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.different_item_layout_type_two, parent, false);
                    viewHolderExpandable.item = (TextView) convertView.findViewById(R.id.tv_title);
                    viewHolderExpandable.toggle = (TextView) convertView.findViewById(R.id.tv_toggle);
                    convertView.setTag(viewHolderExpandable); // view lookup cache stored in tag
                } else {
                    viewHolderExpandable = (ViewHolderExpandable) convertView.getTag();
                }
                viewHolderExpandable.item.setText(item.getName());
                viewHolderExpandable.toggle.setText(item.isExpanded() ? "-" : "+");
                viewHolderExpandable.toggle.setOnClickListener(new View.OnClickListener() {
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
                break;
            default:
                break;
        }

        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Item item = getItem(position);
        if (item.isExpandable()) {
            return VIEW_TYPE_TWO;
        } else {
            return VIEW_TYPE_ONE;
        }
    }

    class ViewHolderSimple {
        private TextView item;
    }

    class ViewHolderExpandable {
        TextView item, toggle;
    }

    public interface ItemListener {
        void onToggle(int pos, boolean expand);
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}