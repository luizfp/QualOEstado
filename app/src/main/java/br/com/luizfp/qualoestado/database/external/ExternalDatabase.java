package br.com.luizfp.qualoestado.database.external;

import android.content.Context;

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