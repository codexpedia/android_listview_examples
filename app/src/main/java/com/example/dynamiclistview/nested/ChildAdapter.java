package com.example.dynamiclistview.nested;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Item> items;
    private boolean isExpanded = false;
    private ItemListener itemListener;

    public ChildAdapter(Context context, ArrayList<Item> childData) {
        this.context = context;
        this.items = childData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChild;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChild = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        ChildAdapter.ViewHolder vh = new ChildAdapter.ViewHolder(itemLayoutView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;

        Item item = items.get(position);
        vh.tvChild.setText(item.getName());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    itemListener.onCollapse();
                    isExpanded = false;
                } else {
                    itemListener.onExpand();
                    isExpanded = true;
                }
                notifyItemChanged(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public interface ItemListener {
        void onExpand();
        void onCollapse();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

}