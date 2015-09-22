package br.com.lfpmobile.qualoestado.util;

import java.text.Normalizer;

/**
 * Created by luiz on 9/21/15.
 */
public class StringUtils {

    public static int getQtdLetrasSemEspaco(String s) {
        s = s.replaceAll(" ", "");
        return s.length();
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}
