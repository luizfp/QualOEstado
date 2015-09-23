package br.com.lfpmobile.qualoestado.dominio;

import br.com.lfpmobile.qualoestado.Constants;

public class DicaDescricao extends Dica{

	public DicaDescricao() {
	}

	public DicaDescricao(Estado estado, boolean jaComprada) {
		super(estado, jaComprada);
	}

	@Override
	public int getCustoEmPontos() {
		return Constants.CUSTO_DICA_DESCRICAO;
	}


}
