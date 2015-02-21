package com.javahelps.sample.customizedlistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private ListView listView;
    private String[] designPatterns;
    private String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the list view
        this.listView = (ListView) findViewById(R.id.listView);

        // Create the arrays
        this.designPatterns = getResources().getStringArray(R.array.design_patterns);
        this.descriptions = getResources().getStringArray(R.array.design_patterns_description);

        // Create an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, designPatterns);
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClicked(position);
            }
        });
    }

    private void onListItemClicked(int index) {
        String description = descriptions[index];
        Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
    }

}
