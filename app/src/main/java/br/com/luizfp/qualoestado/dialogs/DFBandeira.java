package br.com.luizfp.qualoestado.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.models.DicaBandeira;
import br.com.luizfp.qualoestado.util.DrawableUtils;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by luiz on 9/21/15.
 */
public class DFBandeira extends SupportBlurDialogFragment {

    private static final String DICA_BANDEIRA = "DICABANDEIRA";
    private ImageView imgDicaBandeira;
    private DicaBandeira dicaBandeira;

    public DFBandeira() {
    }

    public static DFBandeira newInstance(DicaBandeira dicaBandeira) {
        DFBandeira dialog = new DFBandeira();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DICA_BANDEIRA, dicaBandeira);
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
        dicaBandeira = (DicaBandeira) getArguments().getSerializable(DICA_BANDEIRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dica_bandeira, container, false);
        getDialog().setTitle("Bandeira Estado");
        imgDicaBandeira = (ImageView)view.findViewById(R.id.imgDicaBandeira);
        int resId = DrawableUtils.getImageIdByName(dicaBandeira.getEstado().getNomeImgBandeira(),
                getContext());
        imgDicaBandeira.setImageResource(resId);
        return view;
    }
}
