package br.com.luizfp.qualoestado.models;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.util.List;

import br.com.luizfp.qualoestado.Constants;
import br.com.luizfp.qualoestado.database.DBHelper;
import br.com.luizfp.qualoestado.database.external.EstadoDAOImp;
import br.com.luizfp.qualoestado.database.android.JogadorDAOImp;
import br.com.luizfp.qualoestado.util.StringUtils;

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
     * @param estado O estado para o qual a dica deve ser criada
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
        atualizarMenorNumPontos(jogador);
    }

    public void usouDicaDescricao(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumUsosDicaDescricao(jogador.getNumUsosDicaDescricao());
        atualizarMenorNumPontos(jogador);
    }

    public void usouDicaLetra(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumUsosDicaLetra(jogador.getNumUsosDicaLetra());
        atualizarMenorNumPontos(jogador);
    }

    public void acertouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumAcertos(jogador.getNumAcertos());
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        atualizarMaiorNumPontos(jogador);
    }

    public void errouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumErros(jogador.getNumErros());
        atualizarMenorNumPontos(jogador);
    }

    public void pulouJogada(Jogador jogador) {
        jogadorDAOImp.atualizarNumPontos(jogador.getPontos());
        jogadorDAOImp.atualizarNumPulosResposta(jogador.getNumPulosResposta());
        atualizarMenorNumPontos(jogador);
    }

    public void resetarUsoDicas() {
        this.dicaBandeira.setJaComprada(false);
        this.dicaDescricao.setJaComprada(false);
        this.dicaLetra.setJaComprada(false);
    }


    /**
     * Chamado quando o jogador executa alguma ação que aumente seu número de pontos.
     * @param jogador Objeto que contém os valores mais atuais de jogador
     */
    private void atualizarMaiorNumPontos(Jogador jogador) {
        if (jogador.getPontos() > this.jogador.getMaiorNumPontos()) {
            this.jogador.setMaiorNumPontos(jogador.getPontos());
            jogadorDAOImp.atualizarMaiorNumPontos(jogador.getPontos());
        }
    }

    /**
     * Chamado quando o jogador executa alguma ação que diminua seu número de pontos.
     * @param jogador Objeto que contém os valores mais atuais de jogador
     */
    private void atualizarMenorNumPontos(Jogador jogador) {
        if (jogador.getPontos() < this.jogador.getMenorNumPontos()) {
            this.jogador.setMenorNumPontos(jogador.getPontos());
            jogadorDAOImp.atualizarMenorNumPontos(jogador.getPontos());
        }
    }
}
