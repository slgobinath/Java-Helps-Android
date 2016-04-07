package com.javahelps.imagedatabasedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner cmbName;
    private ImageView imgPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI components
        this.cmbName = (Spinner) findViewById(R.id.cmbName);
        this.imgPlace = (ImageView) findViewById(R.id.imgPlace);

        // Define final variables since they have to be accessed from inner class
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

        // Open the database
        databaseAccess.open();

        // Read all the names
        final List<String> names = databaseAccess.getNames();

        // Close the database
        databaseAccess.close();

        // Create adapter and set to the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cmbName.setAdapter(adapter);

        this.cmbName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected name
                String name = names.get(position);

                // Open the database
                databaseAccess.open();

                // Retrieve the selected image as byte[]
                byte[] data = databaseAccess.getImage(name);
                // Convert to Bitmap
                Bitmap image = toBitmap(data);
                // Set to the imgPlace
                imgPlace.setImageBitmap(image);

                // Close the database
                databaseAccess.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    /**
     * Convert byte[] to Bitmap
     *
     * @param image
     * @return
     */
    public static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
