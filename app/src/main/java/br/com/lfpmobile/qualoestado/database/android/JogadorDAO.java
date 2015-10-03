package br.com.lfpmobile.qualoestado.database.android;

import br.com.lfpmobile.qualoestado.models.Jogador;

/**
 * Created by luiz on 9/23/15.
 */
public interface JogadorDAO {

    Jogador inserirNovoJogador();
    Jogador getJogador();
    void atualizarNumPontos(int numero);
    void atualizarNumAcertos(int numero);
    void atualizarNumErros(int numero);
    void atualizarNumPulosResposta(int numero);
    void atualizarNumUsosDicaBandeira(int numero);
    void atualizarNumUsosDicaDescricao(int numero);
    void atualizarNumUsosDicaLetra(int numero);
    void atualizarMaiorNumPontos(int numero);
    void atualizarMenorNumPontos(int numero);
    void atualizarAppJaUsado(boolean b);
    boolean appJaUsado();
}
