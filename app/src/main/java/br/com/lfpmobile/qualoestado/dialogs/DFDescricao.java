package br.com.lfpmobile.qualoestado.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lfpmobile.qualoestado.R;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by luiz on 9/21/15.
 */
public class DFDescricao extends SupportBlurDialogFragment {

    private static final String DESCRICAO = "DESCRICAO";
    private String descricaoEstado;
    private TextView txtDescricaoEstado;

    public DFDescricao() {
    }

    public static DFDescricao newInstance(String descricao) {
        DFDescricao dialog = new DFDescricao();

        Bundle bundle = new Bundle();
        bundle.putString(DESCRICAO, descricao);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        descricaoEstado = getArguments().getString(DESCRICAO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dica_descricao, container, false);
        getDialog().setTitle("Descrição Estado");
        txtDescricaoEstado = (TextView)view.findViewById(R.id.txtDescricaoEstado);
        txtDescricaoEstado.setText(descricaoEstado);
        txtDescricaoEstado.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}