package br.com.luizfp.qualoestado.database.android;

/**
 * Created by luiz on 9/23/15.
 */
public class ScriptSQL {

    public static String getCreateJogador() {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS JOGADOR ( ");
        sqlBuilder.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("PONTOS INTEGER NOT NULL, ");
        sqlBuilder.append("NOME VARCHAR(50), ");
        sqlBuilder.append("NUM_ACERTOS INTEGER, ");
        sqlBuilder.append("NUM_ERROS INTEGER, ");
        sqlBuilder.append("NUM_PULOS_RESPOSTA INTEGER, ");
        sqlBuilder.append("NUM_USOS_DICA_BANDEIRA INTEGER, ");
        sqlBuilder.append("NUM_USOS_DICA_DESCRICAO INTEGER, ");
        sqlBuilder.append("NUM_USOS_DICA_LETRA INTEGER, ");
        sqlBuilder.append("MAIOR_NUM_PONTOS INTEGER, ");
        sqlBuilder.append("MENOR_NUM_PONTOS INTEGER, ");
        sqlBuilder.append("APP_JA_USADO INTEGER ");
        sqlBuilder.append(");");

        //RETORNA CONTEÚDO DO SCRIPT
        return sqlBuilder.toString();
    }
}