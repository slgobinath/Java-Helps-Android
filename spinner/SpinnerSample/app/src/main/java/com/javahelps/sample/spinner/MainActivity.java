package com.javahelps.sample.spinner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {
    // References to GUI components.
    private Spinner cmbAnimals;
    private ImageView imgAnimal;

    // Animal names
    private final String[] animals = {"Deer", "Elephant", "Jaguar", "Lion", "Tiger", "Wolf"};
    // Animal image animalIDs
    // Both names and image animalIDs are in the same order
    private final int[] animalIDs = {R.drawable.img_deer, R.drawable.img_elephant, R.drawable.img_jaguar, R.drawable.img_lion, R.drawable.img_tiger, R.drawable.img_wolf};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the GUI components.
        this.cmbAnimals = (Spinner) findViewById(R.id.cmbAnimals);
        this.imgAnimal = (ImageView) findViewById(R.id.imgAnimal);

        // Create an array adapter and set it to the Spinner.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.cmbAnimals.setAdapter(adapter);

        // Set the message to default.
        this.cmbAnimals.setSelection(0);

        // Set itm selected listener.
        this.cmbAnimals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imgAnimal.setImageResource(animalIDs[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

}
