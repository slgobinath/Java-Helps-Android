package com.javahelps.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


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
        this.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrderClicked();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
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
        String number = etNumber.getText().toString();  // getText() returns Editable not String
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

        // Show the message
        showMessage(number, message);
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
     * @param number The order number
     * @param order  The descriptive order to display
     */
    private void showMessage(String number, String order) {
        // Create an Intent
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        // Attach the parameters
        intent.putExtra("ORDER_NUMBER", number);
        intent.putExtra("ORDER_MESSAGE", order);
        // Start the activity
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
