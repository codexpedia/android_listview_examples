package com.example.dynamiclistview.nested;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;

import java.util.ArrayList;

public class NestedDynamicListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private ArrayList <Item> itemList;
    private GroupArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_dynamic_list);

        // Initializing list view with the custom adapter
        itemList = new ArrayList<Item>();
        // Populating list items
        for(int i=0; i<20; i++) {
            Item item = new Item("Item " + i);
            if (i % 3 == 0) item.setExpandable(true);
            itemList.add(item);
        }

        itemArrayAdapter = new GroupArrayAdapter(this, itemList);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(itemArrayAdapter);
    }

}
