package br.com.luizfp.qualoestado.database.external;

import java.util.List;

import br.com.luizfp.qualoestado.models.Estado;

/**
 * Created by luiz on 9/23/15.
 */
public interface EstadoDAO {

    List<Estado> getEstados();
}