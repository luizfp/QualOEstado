package br.com.lfpmobile.qualoestado.dominio;

import br.com.lfpmobile.qualoestado.Constants;

public class DicaBandeira extends Dica {
	
	public DicaBandeira() {
	}

    public DicaBandeira(Estado estado, boolean jaComprada) {
        super(estado, jaComprada);
    }

    @Override
    public int getCustoEmPontos() {
        return Constants.CUSTO_DICA_BANDEIRA;
    }
}
