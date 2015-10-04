package br.com.luizfp.qualoestado.models;

import java.io.Serializable;

import br.com.luizfp.qualoestado.Constants;

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
