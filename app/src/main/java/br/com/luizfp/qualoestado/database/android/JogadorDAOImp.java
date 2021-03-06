package br.com.luizfp.qualoestado.database.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import br.com.luizfp.qualoestado.Constants;
import br.com.luizfp.qualoestado.database.DBHelper;
import br.com.luizfp.qualoestado.models.Jogador;

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
            COLUNA_MENOR_NUM_PONTOS = "MENOR_NUM_PONTOS",
            COLUNA_APP_JA_USADO = "APP_JA_USADO";

    public JogadorDAOImp() {
    }

    public JogadorDAOImp(final Context context) {
        this.context = context;
    }

    @Override
    public final Jogador inserirNovoJogador() {
        String inserirJogador = "INSERT INTO JOGADOR (PONTOS, NUM_ACERTOS, NUM_ERROS,"
                + " NUM_PULOS_RESPOSTA, NUM_USOS_DICA_BANDEIRA, NUM_USOS_DICA_DESCRICAO,"
                + " NUM_USOS_DICA_LETRA, MAIOR_NUM_PONTOS, MENOR_NUM_PONTOS, APP_JA_USADO)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(inserirJogador);
        stmt.bindLong(1, Constants.VALOR_INICIAL_PONTOS_JOGADOR);
        stmt.bindLong(2, 0); // Número inicial de acertos
        stmt.bindLong(3, 0); // Número inicial de erros
        stmt.bindLong(4, 0); // Número inicial de pulos resposta
        stmt.bindLong(5, 0); // Número inicial de usos da dica bandeira
        stmt.bindLong(6, 0); // Número inicial de usos da dica descricao
        stmt.bindLong(7, 0); // Número inicial de usos da dica letra
        stmt.bindLong(8, Constants.VALOR_INICIAL_PONTOS_JOGADOR); // Maior número de pontos já obtido
        stmt.bindLong(9, Constants.VALOR_INICIAL_PONTOS_JOGADOR); // Menor número de pontos já obtido
        stmt.bindLong(10, 0); // App já usado. 1 == true, 0 == false
        stmt.execute();
        DBHelper.close(db, sqLiteDatabase, null, stmt);
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
        if (c.isNull(c.getColumnIndex(COLUNA_PONTOS)))
            jogador.setPontos(-1);
        else
            jogador.setPontos(c.getInt(c.getColumnIndex(COLUNA_PONTOS)));
        jogador.setNumErros(c.getInt(c.getColumnIndex(COLUNA_NUM_ERROS)));
        jogador.setNumAcertos(c.getInt(c.getColumnIndex(COLUNA_NUM_ACERTOS)));
        jogador.setNumPulosResposta(c.getInt(c.getColumnIndex(COLUNA_NUM_PULOS_RESPOSTA)));
        jogador.setNumUsosDicaBandeira(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_BANDEIRA)));
        jogador.setNumUsosDicaDescricao(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_DESCRICAO)));
        jogador.setNumUsosDicaLetra(c.getInt(c.getColumnIndex(COLUNA_NUM_USOS_DICA_LETRA)));
        jogador.setMaiorNumPontos(c.getInt(c.getColumnIndex(COLUNA_MAIOR_NUM_PONTOS)));
        jogador.setMenorNumPontos(c.getInt(c.getColumnIndex(COLUNA_MENOR_NUM_PONTOS)));
        int flag = c.getInt(c.getColumnIndex(COLUNA_APP_JA_USADO));
        jogador.setAppJaUsado(flag == 1);
        DBHelper.close(db, sqLiteDatabase, c, null);
        return jogador;
    }

    @Override
    public final void atualizarNumPontos(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_PONTOS + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumAcertos(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_ACERTOS + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        // Não funciona
        //sqLiteDatabase.rawQuery(atualizarQuery, new String[] {String.valueOf(numero)});

        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumErros(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_ERROS + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumPulosResposta(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_PULOS_RESPOSTA + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumUsosDicaBandeira(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_USOS_DICA_BANDEIRA + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumUsosDicaDescricao(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_USOS_DICA_DESCRICAO + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarNumUsosDicaLetra(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_NUM_USOS_DICA_LETRA + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarMaiorNumPontos(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_MAIOR_NUM_PONTOS + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final void atualizarMenorNumPontos(int numero) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_MENOR_NUM_PONTOS + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        stmt.bindLong(1, numero);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

    @Override
    public final List getTodasEstatisticas() {
        return null;
    }

    @Override
    public final boolean appJaUsado() {
        String selectQuery = "SELECT " + COLUNA_APP_JA_USADO + " FROM JOGADOR";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, null);
        c.moveToFirst();
        if (c.isNull(c.getColumnIndex(COLUNA_APP_JA_USADO))) {
            return false;
        }
        int flag = c.getInt(c.getColumnIndex(COLUNA_APP_JA_USADO));
        DBHelper.close(db, sqLiteDatabase, c, null);
        return (flag == 1);
    }

    @Override
    public final void atualizarAppJaUsado(final boolean b) {
        String atualizarQuery = "UPDATE JOGADOR SET " + COLUNA_APP_JA_USADO + " = ?";
        AndroidDatabase db = new AndroidDatabase(context);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(atualizarQuery);
        // Se b for true flag recebe 1. Será guardado como integer já que o SQLite não tem classe
        // nativa para boolean.
        int flag = (b) ? 1 : 0;
        stmt.bindLong(1, flag);
        stmt.execute();

        DBHelper.close(db, sqLiteDatabase, null, stmt);
    }

}
