package com.javahelps.problem.activity;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    // References to GUI components.
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox chkRemember;
    private Button btnLogin;

    // Username and Password constants.
    private static final String USERNAME = "javahelps";
    private static final String PASSWORD = "abc123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the GUI components.
        findViews();

        // Set the OnClickListener.
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked();
            }
        });
    }

    /**
     * Find the GUI components.
     */
    private void findViews() {
        this.etUsername = (EditText) findViewById(R.id.etUsername);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void onLoginClicked() {
        String inputUsername = etUsername.getText().toString();
        String inputPassword = etPassword.getText().toString();
        boolean remember = chkRemember.isChecked();

        if (USERNAME.equals(inputUsername) && PASSWORD.equals(inputPassword)) {
            // Login succeed.
            // TODO: Create and start the intent

        } else {
            // Login failed.
            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
        }
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
