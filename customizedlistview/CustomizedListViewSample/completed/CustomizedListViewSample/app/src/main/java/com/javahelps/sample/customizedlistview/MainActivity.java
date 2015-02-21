package com.javahelps.sample.customizedlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
        ArrayAdapter<String> adapter = new CustomAdapter(this, designPatterns);
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

    private class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(Context context, String[] objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Create convertView only for the first time
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
            }
            // Find the ImageView from convertView
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            // Find the TextView from convertView
            TextView txtItem = (TextView) convertView.findViewById(R.id.txtItem);
            // According to the position of item determine the icon
            int drawable;
            if (position >= 0 && position <= 4) {
                // Creational design pattern
                drawable = R.drawable.img_creational;
            } else if (position >= 5 && position <= 11) {
                // Structural design pattern
                drawable = R.drawable.img_structural;
            } else {
                // Behavioral design pattern
                drawable = R.drawable.img_behavioural;
            }
            // Set the icon to ImageView
            imgIcon.setImageResource(drawable);
            // Set the design pattern name to TextView
            txtItem.setText(designPatterns[position]);

            return convertView;
        }
    }

}
