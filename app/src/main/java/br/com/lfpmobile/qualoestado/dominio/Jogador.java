package br.com.lfpmobile.qualoestado.dominio;

public class Jogador {
	
	private String nome;
	private int pontuacao;
	private int numErros;
	private int numAcertor;
	
	public Jogador() {
		
	}
	
	public Jogador(String nome, int pontuacao, int numErros, int numAcertor) {
		this.nome = nome;
		this.pontuacao = pontuacao;
		this.numErros = numErros;
		this.numAcertor = numAcertor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getNumErros() {
		return numErros;
	}

	public void setNumErros(int numErros) {
		this.numErros = numErros;
	}

	public int getNumAcertor() {
		return numAcertor;
	}

	public void setNumAcertor(int numAcertor) {
		this.numAcertor = numAcertor;
	}
	
}
