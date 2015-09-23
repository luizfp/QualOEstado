package br.com.lfpmobile.qualoestado.dominio;

import android.content.Context;

import java.util.List;

import br.com.lfpmobile.qualoestado.database.RepositorioEstado;
import br.com.lfpmobile.qualoestado.util.StringUtils;

public class Gerenciador {

	private Jogador jogador;
	private DicaBandeira dicaBandeira;
    private DicaDescricao dicaDescricao;
    private DicaLetra dicaLetra;
	private List<Estado> listaEstados;
	
	public Gerenciador() {
	}

    public void iniciarJogo(Context context) {
        // Criar Jogador
        buscarEstados(context);
        criarDicas();
    }

    private void buscarEstados(Context context) {
        RepositorioEstado repositorioEstado = new RepositorioEstado(context);
        listaEstados = repositorioEstado.getEstados();
    }

    private void criarDicas() {
        dicaBandeira = new DicaBandeira(listaEstados.get(0), false);
        dicaDescricao = new DicaDescricao(listaEstados.get(0), false);
        dicaLetra = new DicaLetra(listaEstados.get(0), false);
    }

    public void recriarDicas(Estado estado) {
        dicaBandeira.setEstado(estado);
        dicaDescricao.setEstado(estado);
        dicaLetra.setEstado(estado);
    }

	public boolean confirmaJogada(String jogadaUsuario, String nomeEstado) {
		jogadaUsuario = StringUtils.stripAccents(jogadaUsuario).toLowerCase().trim();
		nomeEstado = StringUtils.stripAccents(nomeEstado).toLowerCase().trim();

		return jogadaUsuario.equals(nomeEstado);
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

}
