package br.com.lfpmobile.qualoestado;

import android.app.Application;

/**
 * Created by luiz on 10/3/15.
 */
public class QualOEstadoApp extends Application {

    /* Um pouco de trapassa para não parar a música nas trocas de activities.
     * Váriavel global será alterada para true toda vida que um botão for clicado
     * (único modo de trocar de activity). Então será realizada uma checagem no
     * método onPause de cada activity (cada uma que se possa ir para outra)
     * para se a variável for true então significa que é uma troca de serviço
     * e a música de fundo não deve ser parada.
     */
    private boolean trocaActivity;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public boolean isTrocaActivity() {
        return trocaActivity;
    }

    public void setTrocaActivity(boolean trocaActivity) {
        this.trocaActivity = trocaActivity;
    }
}
