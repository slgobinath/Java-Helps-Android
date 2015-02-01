package com.javahelps.sample.events;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    // References to GUI components
    private EditText etNumber;
    private RadioButton optDecaf;
    private RadioButton optExpresso;
    private RadioButton optColombian;
    private CheckBox chkCream;
    private CheckBox chkSugar;
    private Button btnOrder;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Find the GUI components
        findViews();

        // 2. Set the on click listeners for buttons
    }

    /**
     * Find the GUI components at the runtime.
     */
    private void findViews() {
        this.etNumber = null;
        this.optColombian = null;
        this.optExpresso = null;
        this.optDecaf = null;
        this.chkCream = null;
        this.chkSugar = null;
        this.btnCancel = null;
        this.btnOrder = null;
    }

    /**
     * The logic to execute when the btnOrder is clicked.
     */
    private void onOrderClicked() {
        String coffee;
        String message;
        // EditText.getText() returns Editable not String
        String number = "";
        // Check the CheckBoxes
        boolean cream = false;
        boolean sugar = false;

        // Check the RadioButtons for selection
        if (true) {
            coffee = "Expresso";
        } else if (optColombian.isChecked()) {
            coffee = "Colombian";
        } else {
            coffee = "Decaf";
        }

        // Create a message
        if (cream && sugar) {
            message = coffee + " with cream and sugar.";
        } else if (cream) {
            message = coffee + " with cream.";
        } else if (sugar) {
            message = coffee + " with sugar.";
        } else {
            message = coffee + ".";
        }

        message = "Order no: " + number + ".\n" + message;

        // Show the message
        showMessage(message);
    }

    /**
     * The logic to execute when the btnCancel is clicked.
     */
    private void onCancelClicked() {
        this.finish();  // Close the application.
    }

    /**
     * Display the message to user.
     *
     * @param message The message to display
     */
    private void showMessage(String message) {
        // Show a pop up message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
