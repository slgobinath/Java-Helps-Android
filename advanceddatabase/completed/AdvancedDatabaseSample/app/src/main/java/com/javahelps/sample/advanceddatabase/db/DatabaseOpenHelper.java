package com.javahelps.sample.advanceddatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gobinath on 3/27/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts.db";
    private static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Contact(first_name TEXT, last_name TEXT, phone TEXT PRIMARY KEY, email TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
