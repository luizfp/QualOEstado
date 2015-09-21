package br.com.lfpmobile.qualoestado.dominio;

/**
 * Created by luiz on 9/20/15.
 */
public class RandomGenerate {

    public RandomGenerate() {
    }

    /**
     * Gera números aleatórios dentre uma faixa especificada.
     * Faixa de: [min, max).
     *
     * @param min
     *  O valor mínimo da faixa, incluindo ele pŕoprio.
     * @param max
     *  O valor máximo da faixa, sem incluir ele próprio.
     * @return int
     */
    public int generateInRange(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }
}
