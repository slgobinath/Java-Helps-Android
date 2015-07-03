package com.javahelps.memo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gobinath on 3/22/15.
 */
class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "memos.db";
    public static final String TABLE = "memo";
    public static final int VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo(date INTEGER PRIMARY KEY, memo TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
