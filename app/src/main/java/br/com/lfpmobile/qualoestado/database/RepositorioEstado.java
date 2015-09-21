package br.com.lfpmobile.qualoestado.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.lfpmobile.qualoestado.dominio.Estado;
import br.com.lfpmobile.qualoestado.util.ListUtils;

/**
 * Created by luiz on 9/21/15.
 */
public class RepositorioEstado {

    private SQLiteDatabase sqLiteDatabase;
    private MyDatabase db;
    private Context context;
    private static final String COLUNA_NOME = "NOMEESTADO";
    private static final String COLUNA_DESCRICAO = "DESCRICAO";
    private static final String COLUNA_NOME_IMG_MAPA = "NOMEIMGMAPA";
    private static final String COLUNA_NOME_IMG_BANDEIRA = "NOMEIMGBANDEIRA";

    public RepositorioEstado(Context context) {
        this.context = context;
    }

    public List<Estado> getEstados() {
        List<Estado> listEstados = new ArrayList<>();
        db = new MyDatabase(context);
        sqLiteDatabase = db.getReadableDatabase();
        String selectQuery = "SELECT * FROM ESTADO";
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Estado estado = new Estado();
                estado.setNome(c.getString(c.getColumnIndex(COLUNA_NOME)));
                estado.setDescricao(c.getString(c.getColumnIndex(COLUNA_DESCRICAO)));
                estado.setNomeImgMapa(c.getString(c.getColumnIndex(COLUNA_NOME_IMG_MAPA)));
                estado.setNomeImgBandeira(c.getString(c.getColumnIndex(COLUNA_NOME_IMG_BANDEIRA)));
                listEstados.add(estado);
            } while (c.moveToNext());
        }
        c.close();
        sqLiteDatabase.close();
        db.close();
        return ListUtils.shuffleList(listEstados);
    }
}
