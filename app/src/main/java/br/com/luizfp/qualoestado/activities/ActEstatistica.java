package br.com.luizfp.qualoestado.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.luizfp.qualoestado.QualOEstadoApp;
import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.app.BackgroundSoundService;
import br.com.luizfp.qualoestado.models.Gerenciador;
import br.com.luizfp.qualoestado.models.Jogador;

public class ActEstatistica extends BaseActivity {

    boolean stopMusicService = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_estatistica);
        setupActionBar();

        TextView txtStatsNumAcertos = (TextView)findViewById(R.id.txtStatsNumAcertos);
        TextView txtStatsNumErros = (TextView)findViewById(R.id.txtStatsNumErros);
        TextView txtStatsNumPulos = (TextView)findViewById(R.id.txtStatsNumPulos);
        TextView txtStatsNumUsosDicaBandeira = (TextView)findViewById(R.id.txtStatsNumUsosDicaBandeira);
        TextView txtStatsNumUsosDicaDescricao = (TextView)findViewById(R.id.txtStatsNumUsosDicaDescricao);
        TextView txtStatsNumUsosDicaLetra = (TextView)findViewById(R.id.txtStatsNumUsosDicaLetra);
        TextView txtStatsMaiorNumPontos = (TextView)findViewById(R.id.txtStatsMaiorNumPontos);
        TextView txtStatsMenorNumPontos = (TextView)findViewById(R.id.txtStatsMenorNumPontos);

        Gerenciador gerenciador = Gerenciador.getInstance();
        gerenciador.buscarJogador();
        Jogador jogador = gerenciador.getJogador();

        txtStatsNumAcertos.setText(toString(jogador.getNumAcertos()));
        txtStatsNumErros.setText(toString(jogador.getNumErros()));
        txtStatsNumPulos.setText(toString(jogador.getNumPulosResposta()));
        txtStatsNumUsosDicaBandeira.setText(toString(jogador.getNumUsosDicaBandeira()));
        txtStatsNumUsosDicaDescricao.setText(toString(jogador.getNumUsosDicaDescricao()));
        txtStatsNumUsosDicaLetra.setText(toString(jogador.getNumUsosDicaLetra()));
        txtStatsMaiorNumPontos.setText(toString(jogador.getMaiorNumPontos()));
        txtStatsMenorNumPontos.setText(toString(jogador.getMenorNumPontos()));
    }

    private String toString(int toString) {
        return String.valueOf(toString);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(((QualOEstadoApp)getApplication()).isTrocaActivity()) &&
                ((QualOEstadoApp)getApplication()).isPlayBgMusic())
            startService(BackgroundSoundService.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopMusicService = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stopMusicService)
            stopService(BackgroundSoundService.class);
        ((QualOEstadoApp)getApplication()).setTrocaActivity(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_estatistica, menu);
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

        if (id == android.R.id.home) {
            stopMusicService = false;
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
