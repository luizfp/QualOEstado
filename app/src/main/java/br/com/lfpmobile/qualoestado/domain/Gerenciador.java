package br.com.lfpmobile.qualoestado.domain;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.util.List;

import br.com.lfpmobile.qualoestado.Constants;
import br.com.lfpmobile.qualoestado.database.DBHelper;
import br.com.lfpmobile.qualoestado.database.external.EstadoDAOImp;
import br.com.lfpmobile.qualoestado.database.android.JogadorDAOImp;
import br.com.lfpmobile.qualoestado.util.StringUtils;

public class Gerenciador {

	private Jogador jogador;
	private DicaBandeira dicaBandeira;
    private DicaDescricao dicaDescricao;
    private DicaLetra dicaLetra;
	private List<Estado> listaEstados;
	private static Gerenciador uniqueInstance;
    private JogadorDAOImp jogadorDAOImp;
    private EstadoDAOImp estadoDAOImp;

	private Gerenciador() {
	}

	public static synchronized Gerenciador getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Gerenciador();
		return uniqueInstance;
	}

	public void iniciarJogo() {
        buscarEstados();
        criarDicas();
    }

    public void buscarJogador() {
        jogador = jogadorDAOImp.getJogador();
    }

    private void buscarEstados() {
        listaEstados = estadoDAOImp.getEstados();
    }

    private void criarDicas() {
        dicaBandeira = new DicaBandeira(listaEstados.get(0), false);
        dicaDescricao = new DicaDescricao(listaEstados.get(0), false);
        dicaLetra = new DicaLetra(listaEstados.get(0), false);
    }

    /**
     * Esse método deve ser chamado toda vez que o estado exibido na tela mudar. Para que as dicas
     * possam ser recriadas com base no estado atual.
     * @param estado
     */
    public void recriarDicas(Estado estado) {
        dicaBandeira = new DicaBandeira(estado, false);
        dicaDescricao = new DicaDescricao(estado, false);
        dicaLetra = new DicaLetra(estado, false);
    }

	public boolean confirmaJogada(String jogadaUsuario, String nomeEstado, String siglaEstado) {
		jogadaUsuario = StringUtils.stripAccents(jogadaUsuario).trim().toUpperCase();
		nomeEstado = StringUtils.stripAccents(nomeEstado).trim().toUpperCase();
        Log.i("SIGLA", siglaEstado);

		return jogadaUsuario.equals(nomeEstado) || jogadaUsuario.equals(siglaEstado);
	}

    public boolean doesDatabaseExist(Context context) {
        return (DBHelper.doesDatabaseExist((ContextWrapper) context, Constants.DB_ANDROID_NAME));
    }

    public Jogador inserirJogador() {
        jogador = jogadorDAOImp.inserirNovoJogador();
        return jogador;
    }

    public void atualizarPontos(int numero) {
    }

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

    public void instanciarDAO(Context context) {
        estadoDAOImp = new EstadoDAOImp(context);
        jogadorDAOImp = new JogadorDAOImp(context);
    }

    public DicaBandeira getDicaBandeira() {
        return dicaBandeira;
    }

    public DicaDescricao getDicaDescricao() {
        return dicaDescricao;
    }

    public DicaLetra getDicaLetra() {
        return dicaLetra;
    }

    // O jogador que é passado ja tem os pontos atualizados
    public void usouDicaBandeira(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumUsosDicaBandeira(jogador.getNumUsosDicaBandeira());
    }

    public void usouDicaDescricao(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumUsosDicaDescricao(jogador.getNumUsosDicaDescricao());
    }

    public void usouDicaLetra(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumUsosDicaLetra(jogador.getNumUsosDicaLetra());
    }

    public void acertouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumAcertos(jogador.getNumAcertos());
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
    }

    public void errouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumErros(jogador.getNumErros());
    }

    public void pulouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumPulosResposta(jogador.getNumPulosResposta());
    }

    public void resetarUsoDicas() {
        this.dicaBandeira.setJaComprada(false);
        this.dicaDescricao.setJaComprada(false);
        this.dicaLetra.setJaComprada(false);
    }
}
