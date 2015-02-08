package com.javahelps.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class OrderActivity extends ActionBarActivity {
    /**
     * References to GUI components.
     */
    private TextView txtOrderNumber;
    private TextView txtOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Find the GUI components
        this.txtOrder = (TextView) findViewById(R.id.txtOrder);
        this.txtOrderNumber = (TextView) findViewById(R.id.txtOrderNumber);

        // Get the intent used to start this activity
        Intent intent = getIntent();
        // Get the bundle object from the Intent
        Bundle bundle = intent.getExtras();
        String number;
        String order;
        // Get all the stored parameters
        if (bundle != null) {
            number = bundle.getString("ORDER_NUMBER");
            order = bundle.getString("ORDER_MESSAGE");
        } else {
            number = "Not available";
            order = "Not available.";
        }

        txtOrderNumber.setText(number);
        txtOrder.setText(order);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
