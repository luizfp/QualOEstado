package br.com.lfpmobile.qualoestado.dominio;

import android.content.Context;

import java.util.List;

import br.com.lfpmobile.qualoestado.database.RepositorioEstado;
import br.com.lfpmobile.qualoestado.util.StringUtils;

public class Gerenciador {

	private Jogador jogador;
	private Dica dica;
	private List<Estado> listaEstados;
	
	public Gerenciador() {
	}

	public void buscarEstados(Context context) {
		RepositorioEstado repositorioEstado = new RepositorioEstado(context);
		listaEstados = repositorioEstado.getEstados();
	}

	public boolean confirmaJogada(String jogadaUsuario, String nomeEstado) {
		jogadaUsuario = StringUtils.removeWhitespaces(jogadaUsuario);
		jogadaUsuario = StringUtils.stripAccents(jogadaUsuario).toLowerCase();
		nomeEstado = StringUtils.removeWhitespaces(nomeEstado);
		nomeEstado = StringUtils.stripAccents(nomeEstado).toLowerCase();

		return jogadaUsuario.equals(nomeEstado);
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Dica getDica() {
		return dica;
	}

	public void setDica(Dica dica) {
		this.dica = dica;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

}
