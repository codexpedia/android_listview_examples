package com.example.dynamiclistview.multipl_views_in_list_view_item;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.dynamiclistview.Item;
import com.example.dynamiclistview.R;
import java.util.ArrayList;

public class MultipleViewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lvList;
    private ArrayList <Item> itemList;
    private MultiViewsItemAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_views_list_item);

        // Initializing list view with the custom adapter
        itemList = new ArrayList<Item>();

        itemArrayAdapter = new MultiViewsItemAdapter(this, R.layout.multiple_views_list_item, itemList);
        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setAdapter(itemArrayAdapter);

        // Populating list items
        for(int i=0; i<20; i++) {
            Item item = new Item("Item " + i);
            itemList.add(item);
        }

        // Set up list item onclick listener
        lvList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getBaseContext(), "item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }
}
