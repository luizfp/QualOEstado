package br.com.lfpmobile.qualoestado.database.external;

import java.util.List;

import br.com.lfpmobile.qualoestado.domain.Estado;

/**
 * Created by luiz on 9/23/15.
 */
public interface EstadoDAO {

    List<Estado> getEstados();
}