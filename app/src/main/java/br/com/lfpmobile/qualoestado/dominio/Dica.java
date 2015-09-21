package br.com.lfpmobile.qualoestado.dominio;


public abstract class Dica {
	
	private int custoEmPontos;
	private Estado estado;

	public Dica() {
	}
	
	public Dica(int custoEmPontos, Estado estado) {
		this.custoEmPontos = custoEmPontos;
		this.estado = estado;
	}

	public int getCustoEmPontos() {
		return custoEmPontos;
	}

	public void setCustoEmPontos(int custoEmPontos) {
		this.custoEmPontos = custoEmPontos;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}	
	
}
