package com.javahelps.importexternaldatabase;

import android.content.Context;

import com.javahelps.externalsqliteimporter.ExternalSQLiteOpenHelper;

public class DatabaseOpenHelper extends ExternalSQLiteOpenHelper {
    /**
     * Name of the database.
     */
    public static final String DATABASE_NAME = "quotes.db";

    /**
     * Version of the database. Only used to import from assets.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Use this constructor if you want to import database from assets/database directory.
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Use this constructor if you want to import database from external directory.
     */
    public DatabaseOpenHelper(Context context, String sourceDirectory) {
        super(context, DATABASE_NAME, sourceDirectory, null);
    }
}
