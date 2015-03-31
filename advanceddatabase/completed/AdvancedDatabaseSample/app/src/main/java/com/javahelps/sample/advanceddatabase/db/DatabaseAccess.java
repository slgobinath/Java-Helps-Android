package com.javahelps.sample.advanceddatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.javahelps.sample.advanceddatabase.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gobinath on 3/27/15.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Insert a contact into the database.
     *
     * @param contact the contact to be inserted
     */
    public void insertContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("first_name", contact.getFirstName());
        values.put("last_name", contact.getLastName());
        values.put("phone", contact.getPhone());
        values.put("email", contact.getEmail());
        database.insert("Contact", null, values);
    }

    /**
     * Read all contacts from the database.
     *
     * @return a List of Contacts
     */
    public List<Contact> getContacts() {
        List<Contact> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Contact", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = new Contact();
            contact.setFirstName(cursor.getString(0));
            contact.setLastName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            list.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Update the contact details.
     *
     * @param oldContact the old contact to be replaced
     * @param newContact the new contact to replace
     */
    public void updateContact(Contact oldContact, Contact newContact) {
        ContentValues values = new ContentValues();
        values.put("first_name", newContact.getFirstName());
        values.put("last_name", newContact.getLastName());
        values.put("phone", newContact.getPhone());
        values.put("email", newContact.getEmail());
        database.update("Contact", values, "phone = ?", new String[]{oldContact.getPhone()});
    }

    /**
     * Delete the provided contact.
     *
     * @param contact the contact to delete
     */
    public void deleteContact(Contact contact) {
        database.delete("Contact", "phone = ?", new String[]{contact.getPhone()});
        database.close();
    }
}
