package br.com.lfpmobile.qualoestado.database;

import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.io.File;

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

    public static void close(AndroidDatabase db, SQLiteDatabase sqLiteDatabase, Cursor cursor,
                             SQLiteStatement statement) {
        if (db != null)
            db.close();
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        if (cursor != null)
            cursor.close();
        if (statement != null)
            statement.close();
    }

    public static boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}
