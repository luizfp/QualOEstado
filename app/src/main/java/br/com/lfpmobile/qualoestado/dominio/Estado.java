package br.com.lfpmobile.qualoestado.dominio;

public class Estado {
	
	private String nome;
	private String nomeImgMapa;
	private String nomeImgBandeira;
	private String descricao;
	private int valorEmPontos;
	
	public Estado() {
	}

	public Estado(String nome, String nomeImgMapa, String nomeImgBandeira, String descricao,
				  int valorEmPontos) {
		this.nome = nome;
		this.nomeImgMapa = nomeImgMapa;
		this.nomeImgBandeira = nomeImgBandeira;
		this.descricao = descricao;
		this.valorEmPontos = valorEmPontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeImgMapa() {
		return nomeImgMapa;
	}

	public void setNomeImgMapa(String nomeImgMapa) {
		this.nomeImgMapa = nomeImgMapa;
	}

	public String getNomeImgBandeira() {
		return nomeImgBandeira;
	}

	public void setNomeImgBandeira(String nomeImgBandeira) {
		this.nomeImgBandeira = nomeImgBandeira;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getValorEmPontos() {
		return valorEmPontos;
	}

	public void setValorEmPontos(int valorEmPontos) {
		this.valorEmPontos = valorEmPontos;
	}
}
