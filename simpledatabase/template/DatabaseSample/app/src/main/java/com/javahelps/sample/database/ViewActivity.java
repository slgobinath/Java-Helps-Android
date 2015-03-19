package com.javahelps.sample.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.javahelps.sample.database.model.Contact;


public class ViewActivity extends ActionBarActivity {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private Button btnSave;
    private Button btnDelete;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Find the GUI components
        findViews();

        checkIntentForContact();

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact == null) {
                    insertContact();
                } else {
                    updateContact();
                }
            }
        });

        this.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });
    }

    private void updateContact() {
        // TODO: Update the contact
        this.finish();
    }

    private void insertContact() {
        // TODO: Save the contact
        this.finish();
    }

    private void deleteContact() {
        // TODO: Delete the contact
        this.finish();
    }

    private void findViews() {
        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etPhone = (EditText) findViewById(R.id.etPhone);
        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.btnDelete = (Button) findViewById(R.id.btnDelete);
    }

    private void checkIntentForContact() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            contact = (Contact) bundle.get("CONTACT");
            if (contact != null) {
                this.etFirstName.setText(contact.getFirstName());
                this.etLastName.setText(contact.getLastName());
                this.etPhone.setText(contact.getPhone());
                this.etEmail.setText(contact.getEmail());
                btnDelete.setEnabled(true);
            } else {
                // Add new student
                btnDelete.setEnabled(false);
            }
        }
    }
}
