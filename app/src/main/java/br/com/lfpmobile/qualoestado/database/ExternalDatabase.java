package br.com.lfpmobile.qualoestado.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by luiz on 9/20/15.
 */
public class ExternalDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "QualOEstado.db";
    private static final int DATABASE_VERSION = 1;

    public ExternalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}