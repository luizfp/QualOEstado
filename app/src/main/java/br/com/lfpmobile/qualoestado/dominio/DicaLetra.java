package br.com.lfpmobile.qualoestado.dominio;

import java.io.Serializable;

import br.com.lfpmobile.qualoestado.Constants;

public class DicaLetra extends Dica implements Serializable {

	public DicaLetra() {
	}

	public DicaLetra(Estado estado, boolean jaComprada) {
		super(estado, jaComprada);
	}

	@Override
	public int getCustoEmPontos() {
		return Constants.CUSTO_DICA_LETRA;
	}

}
