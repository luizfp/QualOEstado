package br.com.lfpmobile.qualoestado.database.interfaces;

import java.util.List;

import br.com.lfpmobile.qualoestado.dominio.Estado;

/**
 * Created by luiz on 9/23/15.
 */
public interface EstadoDAO {

    List<Estado> getEstados();
}