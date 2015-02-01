package com.javahelps.sample.events;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrderClicked();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });
    }

    /**
     * Find the GUI components at the runtime.
     */
    private void findViews() {
        this.etNumber = (EditText) findViewById(R.id.etNumber);
        this.optColombian = (RadioButton) findViewById(R.id.optColombian);
        this.optExpresso = (RadioButton) findViewById(R.id.optExpresso);
        this.optDecaf = (RadioButton) findViewById(R.id.optDecaf);
        this.chkCream = (CheckBox) findViewById(R.id.chkCream);
        this.chkSugar = (CheckBox) findViewById(R.id.chkSugar);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);
        this.btnOrder = (Button) findViewById(R.id.btnOrder);
    }

    /**
     * The logic to execute when the btnOrder is clicked.
     */
    private void onOrderClicked() {
        String coffee;
        String message;
        // EditText.getText() returns Editable not String
        String number = etNumber.getText().toString();
        // Check the CheckBoxes
        boolean cream = chkCream.isChecked();
        boolean sugar = chkSugar.isChecked();

        // Check the RadioButtons for selection
        if (optExpresso.isChecked()) {
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
