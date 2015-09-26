package br.com.lfpmobile.qualoestado.database.external;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.lfpmobile.qualoestado.database.DBHelper;
import br.com.lfpmobile.qualoestado.domain.Estado;
import br.com.lfpmobile.qualoestado.util.ListUtils;

/**
 * Created by luiz on 9/21/15.
 */
public class EstadoDAOImp implements EstadoDAO{

    private Context context;
    private static final String COLUNA_NOME = "NOMEESTADO";
    private static final String COLUNA_DESCRICAO = "DESCRICAO";
    private static final String COLUNA_NOME_IMG_MAPA = "NOMEIMGMAPA";
    private static final String COLUNA_NOME_IMG_BANDEIRA = "NOMEIMGBANDEIRA";
    private static final String COLUNA_SIGLA = "SIGLA";

    public EstadoDAOImp(Context context) {
        this.context = context;
    }

    public List<Estado> getEstados() {
        List<Estado> listEstados = new ArrayList<>();
        //TODO tirar essa abertura do bd daqui, botar no gerenciador
        ExternalDatabase db = new ExternalDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String selectQuery = "SELECT * FROM ESTADO";
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Estado estado = new Estado();
                estado.setNome(c.getString(c.getColumnIndex(COLUNA_NOME)));
                estado.setDescricao(c.getString(c.getColumnIndex(COLUNA_DESCRICAO)));
                estado.setNomeImgMapa(c.getString(c.getColumnIndex(COLUNA_NOME_IMG_MAPA)));
                estado.setNomeImgBandeira(c.getString(c.getColumnIndex(COLUNA_NOME_IMG_BANDEIRA)));
                estado.setSigla(c.getString(c.getColumnIndex(COLUNA_SIGLA)));
                listEstados.add(estado);
            } while (c.moveToNext());
        }
        DBHelper.close(db, sqLiteDatabase, c);
        return ListUtils.shuffleList(listEstados);
    }
}
