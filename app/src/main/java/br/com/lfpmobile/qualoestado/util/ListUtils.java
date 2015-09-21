package br.com.lfpmobile.qualoestado.util;

import java.util.Collections;
import java.util.List;

/**
 * Created by luiz on 9/21/15.
 */
public class ListUtils {

    public static List shuffleList(List list) {
        Collections.shuffle(list);
        return list;
    }
}
