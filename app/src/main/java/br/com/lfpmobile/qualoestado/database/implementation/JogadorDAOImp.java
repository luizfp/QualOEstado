package br.com.lfpmobile.qualoestado.database.implementation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import br.com.lfpmobile.qualoestado.database.AndroidDatabase;
import br.com.lfpmobile.qualoestado.database.DBHelper;
import br.com.lfpmobile.qualoestado.database.ExternalDatabase;
import br.com.lfpmobile.qualoestado.database.interfaces.JogadorDAO;
import br.com.lfpmobile.qualoestado.dominio.Jogador;

/**
 * Created by luiz on 9/23/15.
 */
public class JogadorDAOImp implements JogadorDAO {

    private Context context;
    private static final String
            COLUNA_NOME = "NOME",
            COLUNA_PONTOS = "PONTOS",
            COLUNA_NUM_ERROS = "NUM_ERROS",
            COLUNA_NUM_ACERTOS = "NUM_ACERTOS",
            COLUNA_NUM_PULOS_RESPOSTA = "NUM_PULOS_RESPOSTA",
            COLUNA_NUM_USOS_DICA_BANDEIRA = "NUM_USOS_DICA_BANDEIRA",
            COLUNA_NUM_USOS_DICA_DESCRICAO = "NUM_USOS_DICA_DESCRICAO",
            COLUNA_NUM_USOS_DICA_LETRA = "NUM_USOS_DICA_LETRA",
            COLUNA_MAIOR_NUM_PONTOS = "MAIOR_NUM_PONTOS",
            COLUNA_MENOR_NUM_PONTOS = "MENOR_NUM_PONTOS";

    public JogadorDAOImp() {
    }

    public JogadorDAOImp(Context context) {
        this.context = context;
    }

    @Override
    public Jogador inserirNovoJogador() {
        String inserirJogador = "INSERT INTO JOGADOR (PONTOS) VALUES (?)";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(inserirJogador);
        stmt.bindLong(1, 100);
        stmt.execute();
        return getJogador();
    }

    @Override
    public Jogador getJogador() {
        String geJogadorQuery = "SELECT * FROM JOGADOR";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(geJogadorQuery, null);
        c.moveToFirst();
        Jogador jogador = new Jogador();
        // Não há nome definido, então não precisa recuperar essa coluna.
        // jogador.setNome(c.getString(c.getColumnIndex(COLUNA_NOME)));
        jogador.setPontos(c.getInt(c.getColumnIndex(COLUNA_PONTOS)));
        jogador.setNumErros(c.getInt(c.getColumnIndex(COLUNA_NUM_ERROS)));
        jogador.setNumAcertos(c.getInt(c.getColumnIndex(COLUNA_NUM_ACERTOS)));
        jogador.setNumPulosResposta(c.getInt(c.getColumnIndex(COLUNA_NUM_PULOS_RESPOSTA)));
        jogador.setNumUsosDicaBandeira(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_BANDEIRA)));
        jogador.setNumUsosDicaDescricao(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_DESCRICAO)));
        jogador.setNumUsosDicaLetra(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_LETRA)));
        jogador.setMaiorNumPontos(c.getInt(c.getColumnIndex(COLUNA_MAIOR_NUM_PONTOS)));
        jogador.setMenorNumPontos(c.getInt(c.getColumnIndex(COLUNA_MENOR_NUM_PONTOS)));
        DBHelper.close(db, sqLiteDatabase, c);
        return jogador;
    }

    @Override
    public void atualizarNumAcertos(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET NUM_ACERTOS = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        //sqLiteDatabase.rawQuery(atualizarQuery, new String[numero]);

        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        //TESTE

        String select = "SELECT " + COLUNA_NUM_ACERTOS + " FROM JOGADOR";
        Cursor c = sqLiteDatabase.rawQuery(select, null);
        c.moveToFirst();
        String result = String.valueOf(c.getInt(c.getColumnIndex(COLUNA_NUM_ACERTOS)));
        Log.i("DB", result);
        DBHelper.close(db, sqLiteDatabase, null);



    }

    @Override
    public void atualizarNumErros(int numero) {
    }

    @Override
    public void atualizarNumPulosResposta(int numero) {
    }

    @Override
    public void atualizarNumUsosDicaBandeira(int numero) {
    }

    @Override
    public void atualizarNumUsosDicaDescricao(int numero) {
    }

    @Override
    public void atualizarNumUsosDicaLetra(int numero) {
    }

    @Override
    public void atualizarMaiorNumPontos(int numero) {
    }

    @Override
    public void atualizarMenorNumPontos(int numero) {
    }
}
