package com.javahelps.sample.advanceddatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.javahelps.sample.advanceddatabase.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ListView listView;
    private Button btnAdd;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the GUI components
        this.listView = (ListView) findViewById(R.id.listView);
        this.btnAdd = (Button) findViewById(R.id.btnAdd);

        //Set event listener to Button
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        // Set event listener to ListView
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateContact(position);
            }
        });
    }

    /**
     * Read all the contacts
     *
     * @return List of Contacts
     */
    private List<Contact> getContacts() {
        List<Contact> list = new ArrayList<>();
        // TODO: 1. Get the contacts from database
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    private void updateListView() {
        this.contacts = getContacts();

        // Create the adapter and assign to ListView
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        this.listView.setAdapter(adapter);
    }

    /**
     * Start ViewActivity to add new Contact.
     */
    private void addContact() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * Start ViewActivity to update a Contact.
     *
     * @param index the index of the contact
     */
    private void updateContact(int index) {
        Contact contact = contacts.get(index);
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("CONTACT", contact);
        startActivity(intent);
    }

    /**
     * Custom ArrayAdapter for Contacts.
     */
    private class ContactAdapter extends ArrayAdapter<Contact> {


        public ContactAdapter(Context context, List<Contact> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
            }
            TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
            TextView txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
            Contact contact = contacts.get(position);
            txtName.setText(contact.getFirstName());
            txtPhone.setText(contact.getPhone());
            return convertView;
        }
    }
}
