package com.javahelps.problem.events;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

        // TODO: Set the OnClickListener to btnLogin.
    }

    /**
     * Find the GUI components.
     */
    private void findViews() {
        this.etUsername = null;     // TODO: Find the etUserName
        this.etPassword = null;     // TODO: Find the etPassword
        this.chkRemember = null;    // TODO: Find the chkRemember
        this.btnLogin = null;       // TODO: Find the btnLogin
    }

    /**
     * Call this method when the login button is clicked.
     */
    private void onLoginClicked() {
        String inputUsername = "";      // TODO: Get the username
        String inputPassword = "";      // TODO: Get the password
        boolean remember = false;       // TODO: Check the check box
        String message;

        if (USERNAME.equals(inputUsername) && PASSWORD.equals(inputPassword)) {
            // Login succeed.
            if (remember) {
                message = "Welcome to dashboard.\nAutomatic login is activated.";
            } else {
                message = "Welcome to dashboard.";
            }

        } else {
            // Login failed.
            message = "Login failed.";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
