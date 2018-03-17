package com.example.dynamiclistview.different_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;
import com.example.dynamiclistview.simple.ItemArrayAdapter;

import java.util.ArrayList;

public class DifferentLayoutListActivity extends AppCompatActivity implements MultipleLayoutAdapter.ItemListener {

    private ListView lvList;
    private ArrayList <Item> itemList;
    private MultipleLayoutAdapter multipleLayoutAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_layout_list);

        // Initializing list view with the custom adapter
        itemList = new ArrayList<Item>();

        multipleLayoutAdapter = new MultipleLayoutAdapter(this, itemList);
        multipleLayoutAdapter.setItemListener(this);
        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setAdapter(multipleLayoutAdapter);

        // Populating list items
        for(int i=0; i<20; i++) {
            Item item = new Item("Item " + i);
            if (i % 3 == 0) item.setExpandable(true);
            itemList.add(item);
        }

    }

    @Override
    public void onToggle(int pos, boolean expand) {
        int expandSize = 4;
        ArrayList<Item> children = new ArrayList<>();
        for (int i=0; i<expandSize; i++) {
            children.add(new Item("Child " + i));
        }

        if (expand) {
            for (int i=0; i<children.size(); i++) {
                itemList.add(pos + 1 + i, children.get(i));
            }
        } else {
            for (int i=0; i<children.size(); i++) {
                itemList.remove(pos + 1);
            }
        }

        multipleLayoutAdapter.notifyDataSetChanged();
    }
}
