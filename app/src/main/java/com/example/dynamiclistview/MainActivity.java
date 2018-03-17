package com.example.dynamiclistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.dynamiclistview.different_layout.DifferentLayoutListActivity;
import com.example.dynamiclistview.multipl_views_in_list_view_item.MultipleViewsListActivity;
import com.example.dynamiclistview.nested.NestedDynamicListActivity;
import com.example.dynamiclistview.simple.SimpleDynamicListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void simpleDynamicList(View v) {
        startActivity(new Intent(this, SimpleDynamicListActivity.class));
    }

    public void nestedDynamicList(View v) {
        startActivity(new Intent(this, NestedDynamicListActivity.class));
    }

    public void differentLayoutList(View v) {
        startActivity(new Intent(this, DifferentLayoutListActivity.class));
    }

    public void multiViewsItemList(View v) {
        startActivity(new Intent(this, MultipleViewsListActivity.class));
    }
}
