package br.com.lfpmobile.qualoestado.activities;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.database.implementation.JogadorDAOImp;

public class ActMain extends AppCompatActivity {

    private TextView txtPontosJogadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        txtPontosJogadorMenu = (TextView)findViewById(R.id.txtPontosJogadorMenu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCountAnimation();
    }

    //FIXME
    // Funciona apenas da API 3.0 (HONEYCOMB) em diante.
    @SuppressLint("NewApi")
    private void startCountAnimation() {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 727);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                txtPontosJogadorMenu.setText("" + (int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }


    public void iniciarNovoJogo(View view) {
        Intent intent = new Intent(this, ActJogo.class);
        startActivity(intent);
    }

    public void opcoesDeJogo(View view) {
        JogadorDAOImp jogadorDAOImp = new JogadorDAOImp(this);
        jogadorDAOImp.atualizarNumAcertos(200);
        //jogadorDAOImp.inserirNovoJogador();
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
