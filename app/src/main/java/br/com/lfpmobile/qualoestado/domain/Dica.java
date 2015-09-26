package br.com.lfpmobile.qualoestado.domain;


public abstract class Dica {

	private Estado estado;
	private boolean jaComprada;

	public Dica() {
	}
	
	public Dica(Estado estado, boolean jaComprada) {
		this.estado = estado;
		this.jaComprada = jaComprada;
	}

	public abstract int getCustoEmPontos();

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isJaComprada() {
		return jaComprada;
	}

	public void setJaComprada(boolean jaComprada) {
		this.jaComprada = jaComprada;
	}
}
