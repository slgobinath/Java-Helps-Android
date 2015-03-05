package com.javahelps.problem.spinner;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    // References to GUI components.
    private TextView txtWelcome;
    private Spinner cmbLanguages;
    // Constants to represent the languages.
    private static final int ENGLISH = 0;
    private static final int SINHALA = 1;
    private static final int TAMIL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the GUI components.
        this.txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        // TODO: 1. Find the Spinner cmbLanguages and assign it to a variable

        // Get the languages from strings.xml.
        String[] languages = getResources().getStringArray(R.array.languages);

        // TODO: 2. Create an array adapter and set it to the Spinner.

        // Set the message to default (English).
        // TODO: 3. Select the first item (English) in the Spinner as the default language

        changeLanguage(ENGLISH);    // Default message in English

        // TODO: 4. Set OnItemSelectedListener listener and call the changeLanguage method.
    }

    /**
     * Change the language of the welcome message.
     *
     * @param language Constant representation of language.
     */
    private void changeLanguage(int language) {
        Typeface typeface;
        String message;
        switch (language) {
            case SINHALA:
                // Create Sinhala font using font file from assets.
                typeface = Typeface.createFromAsset(getAssets(), "fonts/akandynew.ttf");
                message = getResources().getString(R.string.welcome_sh);    // Read form strings.xml
                break;
            case TAMIL:
                // Create Tamil font using font file from assets.
                typeface = Typeface.createFromAsset(getAssets(), "fonts/baamini.ttf");
                message = getResources().getString(R.string.welcome_ta);    // Read form strings.xml
                break;
            default:
                // Create Sinhala font using font file from assets.
                typeface = Typeface.createFromAsset(getAssets(), "fonts/droidlogo.ttf");
                message = getResources().getString(R.string.welcome_en);    // Read form strings.xml
                break;
        }
        this.txtWelcome.setTypeface(typeface);  // Set the font
        this.txtWelcome.setText(message);       // Set the message
        this.txtWelcome.setTextSize(25.0f);     // Set the font size
    }
}
