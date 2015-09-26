package br.com.lfpmobile.qualoestado.database.android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luiz on 9/23/15.
 */
public class AndroidDatabase extends SQLiteOpenHelper {

    public AndroidDatabase(Context context) {
        //Parâmentros(referência a classe context, nome do banco de dados, null, versão do banco de dados);
        //sempre que houver uma alteração do banco de dados deve-se alterar a versão do banco de dados
        super(context, "JOGADOR", null, 1);
    }

    //Método responsável por criar a tabela de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        //responsável pela criação do banco de dados e da tabela
        db.execSQL(ScriptSQL.getCreateContato());

    }

    //Método responsável por atualizar a tabela de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}