package br.com.lfpmobile.qualoestado.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.util.DrawableUtils;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by luiz on 9/21/15.
 */
public class DFBandeira extends SupportBlurDialogFragment {

    private static final String NOMEIMGBANDEIRA = "NOMEIMGBANDEIRA";
    private String nomeImgBandeira;
    private ImageView imgDicaBandeira;

    public DFBandeira() {
    }

    public static DFBandeira newInstance(String descricao) {
        DFBandeira dialog = new DFBandeira();

        Bundle bundle = new Bundle();
        bundle.putString(NOMEIMGBANDEIRA, descricao);
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

    // onCreate é chamado primeiro do que o onCreateView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nomeImgBandeira = getArguments().getString(NOMEIMGBANDEIRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dica_bandeira, container, false);
        getDialog().setTitle("Bandeira Estado");
        imgDicaBandeira = (ImageView)view.findViewById(R.id.imgDicaBandeira);
        int resId = DrawableUtils.getImageIdByName(nomeImgBandeira, getContext());
        imgDicaBandeira.setImageResource(resId);
        return view;
    }
}
