package br.com.lfpmobile.qualoestado.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.app.CountAnimation;
import br.com.lfpmobile.qualoestado.domain.Gerenciador;
import br.com.lfpmobile.qualoestado.domain.Jogador;
import info.hoang8f.widget.FButton;

public class ActMain extends BaseActivity {

    private TextView txtPontosJogadorMenu;
    private Gerenciador gerenciador;
    private Jogador jogador;
    private FButton btnNovoJogo, btnEstatisticas, btnOpcoes;
    private MediaPlayer mpButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        gerenciador = Gerenciador.getInstance();

        gerenciador.instanciarDAO(this);

        if (!gerenciador.doesDatabaseExist(this)) {
            jogador = gerenciador.inserirJogador();
        } else {
            // Busca o jogador no BD.
            gerenciador.buscarJogador();
            jogador = gerenciador.getJogador();
        }

        txtPontosJogadorMenu = (TextView)findViewById(R.id.txtPontosJogadorMenu);
        btnNovoJogo = (FButton)findViewById(R.id.btnNovoJogo);
        btnEstatisticas = (FButton)findViewById(R.id.btnEstatisticas);
        btnOpcoes = (FButton)findViewById(R.id.btnEstatisticas);

        //set up the button sound
        mpButtonClick = MediaPlayer.create(this, R.raw.button_click);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jogador = gerenciador.getJogador();
        CountAnimation.startCountAnimation(0, jogador.getPontos(), txtPontosJogadorMenu, 1000);
    }

    public void iniciarNovoJogo(View view) {
        mpButtonClick.start();
        openActivity(ActJogo.class);
    }

    public void minhasEstatisticas(View view) {
        mpButtonClick.start();
        openActivity(ActEstatistica.class);
    }

    public void opcoesDeJogo(View view) {
        mpButtonClick.start();
        openActivity(ActOpcoes.class);
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
