package com.example.dynamiclistview.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

public class SimpleDynamicListActivity extends AppCompatActivity implements ItemArrayAdapter.ItemListener{

    private ListView lvList;
    private ArrayList <Item> itemList;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dynamic_list);

        // Initializing list view with the custom adapter
        itemList = new ArrayList<Item>();

        itemArrayAdapter = new ItemArrayAdapter(this, R.layout.simple_list_item, itemList);
        itemArrayAdapter.setItemListener(this);
        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setAdapter(itemArrayAdapter);

        // Populating list items
        for(int i=0; i<20; i++) {
            Item item = new Item("Item " + i);
            if (i % 3 == 0) item.setExpandable(true);
            itemList.add(item);
        }

        // Set up list item onclick listener
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "item " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
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

        itemArrayAdapter.notifyDataSetChanged();
    }

}
