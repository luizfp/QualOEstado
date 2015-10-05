package br.com.luizfp.qualoestado;

import android.app.Application;
import android.util.Log;

import br.com.luizfp.qualoestado.util.PrefUtils;

/**
 * Created by luiz on 10/3/15.
 */
public class QualOEstadoApp extends Application {

    /* Um pouco de trapassa para não parar a música nas trocas de activities.
     * Váriavel global será alterada para true toda vida que um botão for clicado
     * (único modo de trocar de activity). Então será realizada uma checagem no
     * método onPause de cada activity (cada uma que se possa ir para outra)
     * para se a variável for true então significa que é uma troca de activity
     * e a música de fundo não deve ser parada.
     */
    private boolean trocaActivity;
    private boolean playButtonSound;
    private boolean playBgMusic;
    private static final String TAG = "qualoestado";

    @Override
    public void onCreate() {
        playButtonSound = PrefUtils.getInstance(this).getButtonSound();
        playBgMusic = PrefUtils.getInstance(this).isBgMusicEnable();
        super.onCreate();
    }

    public boolean isTrocaActivity() {
        return trocaActivity;
    }

    public void setTrocaActivity(boolean trocaActivity) {
        this.trocaActivity = trocaActivity;
    }

    public boolean isPlayButtonSound() {
        return playButtonSound;
    }

    public boolean isPlayBgMusic() {
        return playBgMusic;
    }

    public void refreshPlayButtonSound() {
        Log.d(TAG, "Refresh called");
        playButtonSound = PrefUtils.getInstance(this).getButtonSound();
    }

    public void refreshPlayBgMusic() {
        playBgMusic = PrefUtils.getInstance(this).isBgMusicEnable();
    }
}
