package br.com.lfpmobile.qualoestado.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.lfpmobile.qualoestado.Constants;
import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.app.CountAnimation;
import br.com.lfpmobile.qualoestado.dialogs.DFBandeira;
import br.com.lfpmobile.qualoestado.dialogs.DFDescricao;
import br.com.lfpmobile.qualoestado.dialogs.DFLetra;
import br.com.lfpmobile.qualoestado.dominio.Estado;
import br.com.lfpmobile.qualoestado.dominio.Gerenciador;
import br.com.lfpmobile.qualoestado.dominio.Jogador;
import br.com.lfpmobile.qualoestado.util.DrawableUtils;
import br.com.lfpmobile.qualoestado.util.ListUtils;

public class ActJogo extends AppCompatActivity {

    private Jogador jogador;
    private EditText edtResposta;
    private ImageView imgEstado;
    private Gerenciador gerenciador;
    private Estado estado;
    private int posicaoLista;
    // 26 e não 27 pois a lista começa em 0;
    private static final int QTD_ESTADOS_LISTA = 26;
    private TextView txtPontosJogadorJogo;
    private boolean jaUsouDicaBandeira = false;
    private boolean jaUsouDicaDescricao = false;
    private boolean jaUsouDicaLetra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jogo);

        edtResposta = (EditText)findViewById(R.id.edtResposta);
        imgEstado = (ImageView)findViewById(R.id.imgEstado);
        txtPontosJogadorJogo = (TextView)findViewById(R.id.txtPontosJogadorJogo);

        edtResposta.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    confirmarResposta();
                    handled = true;
                }
                return handled;
            }
        });

        gerenciador = gerenciador.getInstance();
        gerenciador.iniciarJogo();
        jogador = gerenciador.getJogador();
        posicaoLista = 0;
        setMapaOnScreen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CountAnimation.startCountAnimation(0, jogador.getPontos(), txtPontosJogadorJogo, 2000);
    }

    private void setMapaOnScreen() {
        if (posicaoLista == QTD_ESTADOS_LISTA) {
            // Se jogou todos os estados da lista, reordenar a lista e começar a mostrar novamente
            gerenciador.setListaEstados(ListUtils.shuffleList(gerenciador.getListaEstados()));
            posicaoLista = 0;
        }
        estado = gerenciador.getListaEstados().get(posicaoLista);
        gerenciador.recriarDicas(estado);
        String nomeImgMapa = estado.getNomeImgMapa();
        int resId = DrawableUtils.getImageIdByName(nomeImgMapa, this);
        imgEstado.setImageResource(resId);
        posicaoLista++;
    }

    public void confirmarResposta(View view) {
       confirmarResposta();
    }

    private void confirmarResposta() {
        int novosPontos;
        String resposta = edtResposta.getText().toString().trim();
        if (resposta.isEmpty())
            Toast.makeText(this, "Campo de resposta vazio", Toast.LENGTH_SHORT).show();
        else if (gerenciador.confirmaJogada(resposta, estado.getNome())) {
            novosPontos = jogador.getPontos() + Constants.VALOR_ACERTAR_RESPOSTA;
            CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
            jogador.setPontos(novosPontos);
            jogador.setNumAcertos(jogador.getNumAcertos() + 1);
            gerenciador.acertouJogada(jogador);
            Toast.makeText(this,"Resposta correta!", Toast.LENGTH_SHORT).show();
            edtResposta.setText("");
            setMapaOnScreen();
        } else {
            Toast.makeText(this,"Resposta incorreta!", Toast.LENGTH_SHORT).show();
            novosPontos = jogador.getPontos() - Constants.CUSTO_ERRAR_RESPOSTA;
            CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
            jogador.setPontos(novosPontos);
            jogador.setNumErros(jogador.getNumErros() + 1);
            gerenciador.errouJogada(jogador);
        }
    }

    public void pularEstado(View view) {
        jaUsouDicaBandeira = false;
        jaUsouDicaDescricao = false;
        jaUsouDicaLetra = false;
        int novosPontos = jogador.getPontos() - Constants.CUSTO_PULAR_RESPOSTA;
        CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
        jogador.setPontos(novosPontos);
        jogador.setNumPulosResposta(jogador.getNumPulosResposta() + 1);
        gerenciador.pulouJogada(jogador);
        edtResposta.setText("");
        setMapaOnScreen();
    }

    public void getDicaBandeira(View view) {
        if (!jaUsouDicaBandeira) {
            int novosPontos = jogador.getPontos() - gerenciador.getDicaBandeira().getCustoEmPontos();
            CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
            jogador.setPontos(novosPontos);
            jogador.setNumUsosDicaBandeira(jogador.getNumUsosDicaBandeira() + 1);
            gerenciador.usouDicaBandeira(jogador);
            jaUsouDicaBandeira = true;
        }
        DFBandeira dfBandeira = DFBandeira.newInstance(gerenciador.getDicaBandeira());
        dfBandeira.show(getSupportFragmentManager(), "TAG");

    }

    public void getDicaDescricao(View view) {
        if (!jaUsouDicaDescricao) {
            int novosPontos = jogador.getPontos() - gerenciador.getDicaDescricao().getCustoEmPontos();
            CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
            jogador.setPontos(novosPontos);
            jogador.setNumUsosDicaDescricao(jogador.getNumUsosDicaDescricao() + 1);
            gerenciador.usouDicaDescricao(jogador);
            jaUsouDicaDescricao = true;
        }
        DFDescricao dfDescricao = DFDescricao.newInstance(gerenciador.getDicaDescricao());
        dfDescricao.show(getSupportFragmentManager(), "TAG");
    }

    public void getDicaLetra(View view) {
        if (!jaUsouDicaLetra) {
            int novosPontos = jogador.getPontos() - gerenciador.getDicaLetra().getCustoEmPontos();
            CountAnimation.startCountAnimation(jogador.getPontos(), novosPontos, txtPontosJogadorJogo, 2000);
            jogador.setPontos(novosPontos);
            jogador.setNumUsosDicaLetra(jogador.getNumUsosDicaLetra() + 1);
            gerenciador.usouDicaLetra(jogador);
            jaUsouDicaLetra = true;
        }
        DFLetra dfLetra = DFLetra.newInstance(gerenciador.getDicaLetra());
        dfLetra.show(getSupportFragmentManager(), "TAG");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_jogo, menu);
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
