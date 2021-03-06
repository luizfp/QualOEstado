package br.com.luizfp.qualoestado.database.android;

import java.util.List;

import br.com.luizfp.qualoestado.models.Jogador;

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
    List getTodasEstatisticas();
    boolean appJaUsado();
}
