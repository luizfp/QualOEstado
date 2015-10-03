package br.com.lfpmobile.qualoestado.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.models.DicaLetra;
import br.com.lfpmobile.qualoestado.util.StringUtils;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by luiz on 9/21/15.
 */
public class DFLetra extends SupportBlurDialogFragment {

    private TextView txtLetraInicialNomeEstao;
    private TextView txtQtdLetrasNomeEstado;
    private static final String DICA_LETRA = "DICALETRA";
    private DicaLetra dicaLetra;

    public DFLetra() {
    }

    public static DFLetra newInstance(DicaLetra dicaLetra) {
        DFLetra dialog = new DFLetra();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DICA_LETRA, dicaLetra);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dicaLetra = (DicaLetra) getArguments().getSerializable(DICA_LETRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dica_letra, container, false);
        txtLetraInicialNomeEstao = (TextView)view.findViewById(R.id.txtLetraInicialNomeEstado);
        txtQtdLetrasNomeEstado = (TextView)view.findViewById(R.id.txtQtdLetrasNomeEstado);

        String nomeEstado = dicaLetra.getEstado().getNome();
        String qtdLetrasNome = String.valueOf(StringUtils.getQtdLetrasSemEspaco(nomeEstado));
        txtQtdLetrasNomeEstado.setText("Quantidade de letras: " + qtdLetrasNome);

        txtLetraInicialNomeEstao.setText("Começa com a letra " + "\"" + nomeEstado.charAt(0) + "\"");
        return view;
    }
}
