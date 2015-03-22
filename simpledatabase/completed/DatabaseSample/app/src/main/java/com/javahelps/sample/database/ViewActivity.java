package com.javahelps.sample.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
        SQLiteDatabase database = openOrCreateDatabase("contacts.db", Context.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("first_name", etFirstName.getText().toString());
        values.put("last_name", etLastName.getText().toString());
        values.put("phone", etPhone.getText().toString());
        values.put("email", etEmail.getText().toString());
        database.update("Contact", values, "phone = ?", new String[]{contact.getPhone()});
        database.close();
        this.finish();
    }

    private void insertContact() {
        SQLiteDatabase database = openOrCreateDatabase("contacts.db", Context.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("first_name", etFirstName.getText().toString());
        values.put("last_name", etLastName.getText().toString());
        values.put("phone", etPhone.getText().toString());
        values.put("email", etEmail.getText().toString());
        database.insert("Contact", null, values);
        database.close();
        this.finish();
    }

    private void deleteContact() {
        SQLiteDatabase database = openOrCreateDatabase("contacts.db", Context.MODE_PRIVATE, null);
        database.delete("Contact", "phone = ?", new String[]{contact.getPhone()});
        database.close();
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
        btnDelete.setEnabled(false);
        if (bundle != null) {
            contact = (Contact) bundle.get("CONTACT");
            if (contact != null) {
                this.etFirstName.setText(contact.getFirstName());
                this.etLastName.setText(contact.getLastName());
                this.etPhone.setText(contact.getPhone());
                this.etEmail.setText(contact.getEmail());
                btnDelete.setEnabled(true);
            }
        }
    }
}
