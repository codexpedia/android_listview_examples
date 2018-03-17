package com.example.dynamiclistview.nested;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

public class GroupArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE = 1;
    private static final int EXPANDABLE = 2;

    private Context context;
    private ArrayList<Item> items;

    public GroupArrayAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.items = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == SIMPLE) {
            view   = View.inflate(context, R.layout.group_list_item, null);
            return new ViewHolderSimple(view);
        } else {
            view   = View.inflate(context, R.layout.group_list_item_expandable, null);
            return new ViewHolderExpandable(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);
        Item item = items.get(position);

        if (itemType == SIMPLE) {
            ((ViewHolderSimple)holder).setName(item.getName());
        } else if (itemType == EXPANDABLE) {
            ((ViewHolderExpandable)holder).setData(item);
        }
    }

    @Override
    public int getItemViewType (int position) {
        if (items.get(position).isExpandable()) {
            return EXPANDABLE;
        } else {
            return SIMPLE;
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolderSimple extends RecyclerView.ViewHolder {
        private TextView item;
        public ViewHolderSimple(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setName(String name) {
            item.setText(name);
        }

    }

    class ViewHolderExpandable extends RecyclerView.ViewHolder {
        private ExpandableView expandableView;

        public ViewHolderExpandable(View itemView) {
            super(itemView);
            expandableView = (ExpandableView) itemView.findViewById(R.id.expandable_view);
            expandableView.initViews();
        }

        public void setData(Item item) {
            expandableView.setData(item);
        }
    }

}