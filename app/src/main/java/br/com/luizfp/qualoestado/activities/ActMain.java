package br.com.luizfp.qualoestado.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.luizfp.qualoestado.QualOEstadoApp;
import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.app.BackgroundSoundService;
import br.com.luizfp.qualoestado.app.CountAnimation;
import br.com.luizfp.qualoestado.models.Gerenciador;
import br.com.luizfp.qualoestado.models.Jogador;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;

public class ActMain extends BaseActivity {

    @Bind(R.id.txtPontosJogadorMenu) TextView txtPontosJogadorMenu;
    private Gerenciador gerenciador;
    private Jogador jogador;
    private MediaPlayer mpButtonClick;
    private QualOEstadoApp qualOEstadoApp;
    private boolean playButtonSound;
    private boolean playBgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        gerenciador = Gerenciador.getInstance();

        gerenciador.instanciarDAO(this);

        if (!gerenciador.doesDatabaseExist(this)) {
            jogador = gerenciador.inserirJogador();
        } else {
            // Busca o jogador no BD.
            gerenciador.buscarJogador();
            jogador = gerenciador.getJogador();
        }

        //set up the button sound
        mpButtonClick = MediaPlayer.create(this, R.raw.button_click);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jogador = gerenciador.getJogador();
        qualOEstadoApp = (QualOEstadoApp)getApplication();
        playBgMusic = qualOEstadoApp.isPlayBgMusic();
        playButtonSound = qualOEstadoApp.isPlayButtonSound();
        Log.d(TAG, "playButtonSound is: " + String.valueOf(playButtonSound));
        CountAnimation.startCountAnimation(0, jogador.getPontos(), txtPontosJogadorMenu, 1000);
        if (playBgMusic)
            startService(BackgroundSoundService.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!qualOEstadoApp.isTrocaActivity()) {
            stopService(BackgroundSoundService.class);
        }
    }

    @OnClick(R.id.btnNovoJogo) void iniciarNovoJogo() {
        if (playButtonSound)
            mpButtonClick.start();
        openActivity(ActJogo.class);
        qualOEstadoApp.setTrocaActivity(true);
    }


    @OnClick(R.id.btnEstatisticas) void minhasEstatisticas() {
        if (playButtonSound)
            mpButtonClick.start();
        openActivity(ActEstatistica.class);
        qualOEstadoApp.setTrocaActivity(true);
    }

    @OnClick(R.id.btnOpcoes) void opcoesDeJogo() {
        if (playButtonSound)
            mpButtonClick.start();
        openActivity(ActOpcoes.class);
        qualOEstadoApp.setTrocaActivity(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
