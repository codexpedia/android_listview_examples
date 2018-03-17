package com.example.dynamiclistview.nested;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;
import com.example.dynamiclistview.UIHelper;

import java.util.ArrayList;

public class ExpandableView extends RelativeLayout implements ChildAdapter.ItemListener {

    private RecyclerView rvList;
    private TextView tvToggle;
    private ArrayList<Item> children;
    private static int baseHeight = 0;

    public ExpandableView(Context context) {
        super(context);
    }

    public ExpandableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initViews() {
        rvList = (RecyclerView) findViewById(R.id.rv_expandable);
        tvToggle = (TextView) findViewById(R.id.tv_toggle);
    }

    public void setData(Item item) {
        children = createDummyData(item);
        ChildAdapter childArrayAdapter = new ChildAdapter(getContext(), children);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvList.setLayoutManager(new NestedRecyclerLinearLayoutManager(getContext()));

        rvList.setAdapter(childArrayAdapter);
        childArrayAdapter.setItemListener(this);

        rvList.post(new Runnable() {
            @Override
            public void run() {
                int viewHeight =  Math.round(getContext().getResources().getDimension(R.dimen.list_item_height));
                int collapseAmount = viewHeight * (children.size() - 1);
                UIHelper.collapseWithNoAnimation(ExpandableView.this, collapseAmount);
                baseHeight = getMeasuredHeight() - collapseAmount; // get the base height of the view including the padding/margins of the container
            }
        });
    }


    @Override
    public void onExpand() {
        int from =  (int) getResources().getDimension(R.dimen.list_item_height);
        int to = from * children.size();
        UIHelper.expand(this, from, to);
        tvToggle.setText("-");
    }

    @Override
    public void onCollapse() {
        UIHelper.collapse(this, (int) getResources().getDimension(R.dimen.list_item_height));
        tvToggle.setText("+");
    }


    private ArrayList<Item> createDummyData(Item header) {
        int CHILDREN_SIZE = 3;
        ArrayList<Item> children = new ArrayList<>();
        children.add(header);
        for (int i=0; i<CHILDREN_SIZE; i++) {
            children.add(new Item("Child " + i));
        }
        return children;
    }
}