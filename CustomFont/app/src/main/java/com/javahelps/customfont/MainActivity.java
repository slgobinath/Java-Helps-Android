package com.javahelps.customfont;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView object
        TextView txtMessage = (TextView) findViewById(R.id.txtMessage);
        // Create a TypeFace using the font file
        Typeface typeface = Typeface.createFromAsset(getAssets(), "baamini.ttf");
        // Set a text to the TextView
        txtMessage.setText("tzf;fk;");  // Welcome in Tamil
        // Set the typeface
        txtMessage.setTypeface(typeface);
    }

}
