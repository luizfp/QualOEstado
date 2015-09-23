package br.com.lfpmobile.qualoestado.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by luiz on 9/23/15.
 */
public abstract class DBHelper {

    public static void close(ExternalDatabase db, SQLiteDatabase sqLiteDatabase, Cursor cursor) {
        if (db != null)
            db.close();
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        if (cursor != null)
            cursor.close();
    }

    public static void close(AndroidDatabase db, SQLiteDatabase sqLiteDatabase, Cursor cursor) {
        if (db != null)
            db.close();
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        if (cursor != null)
            cursor.close();
    }
}
