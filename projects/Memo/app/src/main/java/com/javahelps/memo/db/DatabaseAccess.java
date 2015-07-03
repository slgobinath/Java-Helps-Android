package com.javahelps.memo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.javahelps.memo.Memo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gobinath on 3/22/15.
 */
public class DatabaseAccess {
    private SQLiteDatabase database;
    private DatabaseOpenHelper openHelper;
    private static volatile DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void save(Memo memo) {
        ContentValues values = new ContentValues();
        values.put("date", memo.getTime());
        values.put("memo", memo.getText());
        database.insert(DatabaseOpenHelper.TABLE, null, values);
    }

    public void update(Memo memo) {
        ContentValues values = new ContentValues();
        values.put("date", new Date().getTime());
        values.put("memo", memo.getText());
        String date = Long.toString(memo.getTime());
        database.update(DatabaseOpenHelper.TABLE, values, "date = ?", new String[]{date});
    }

    public void delete(Memo memo) {
        String date = Long.toString(memo.getTime());
        database.delete(DatabaseOpenHelper.TABLE, "date = ?", new String[]{date});
    }

    public List<Memo> getAllMemos() {
        List<Memo> memos = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * From memo ORDER BY date DESC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long time = cursor.getLong(0);
            String text = cursor.getString(1);
            memos.add(new Memo(time, text));
            cursor.moveToNext();
        }
        cursor.close();
        return memos;
    }
}
