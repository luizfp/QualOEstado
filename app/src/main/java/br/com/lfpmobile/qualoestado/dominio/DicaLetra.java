package br.com.lfpmobile.qualoestado.dominio;

import java.util.Random;

public class DicaLetra extends Dica {
	
	private static final int LETRA_INICIAL = 0;
	private static final int LETRA_FINAL = 1;
	private static final int LETRA_INICIAL_FINAL = 2;

	public DicaLetra() {
		super();
	}

	public DicaLetra(int custoEmPontos, Estado estado) {
		super(custoEmPontos, estado);
	}
	
	private int quantaAjuda() {
		Random randomGenerator = new Random();
		/* Generate 1 random integer in the range 0..3. */
	    int randomInt = randomGenerator.nextInt(4);
	    if (randomInt == LETRA_INICIAL)
	    	return LETRA_INICIAL;
	    else if (randomInt == LETRA_FINAL)
	    	return LETRA_FINAL;
	    else
	    	return LETRA_INICIAL_FINAL;
	}
	
}
