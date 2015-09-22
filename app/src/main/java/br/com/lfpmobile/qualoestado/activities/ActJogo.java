package br.com.lfpmobile.qualoestado.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.lfpmobile.qualoestado.R;
import br.com.lfpmobile.qualoestado.dialogs.DFBandeira;
import br.com.lfpmobile.qualoestado.dialogs.DFDescricao;
import br.com.lfpmobile.qualoestado.dialogs.DFLetra;
import br.com.lfpmobile.qualoestado.dominio.Estado;
import br.com.lfpmobile.qualoestado.dominio.Gerenciador;
import br.com.lfpmobile.qualoestado.util.DrawableUtils;
import br.com.lfpmobile.qualoestado.util.ListUtils;

public class ActJogo extends AppCompatActivity {

    private EditText edtResposta;
    private ImageView imgEstado;
    private Gerenciador gerenciador;
    private Estado estado;
    private int posicaoLista;
    // 26 e não 27 pois a lista começa em 0;
    private static final int QTD_ESTADOS_LISTA = 26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jogo);

        edtResposta = (EditText)findViewById(R.id.edtResposta);
        imgEstado = (ImageView)findViewById(R.id.imgEstado);
        gerenciador = new Gerenciador();
        gerenciador.buscarEstados(this);
        posicaoLista = 0;
        setMapaOnScreen();
    }

    private void setMapaOnScreen() {
        if (posicaoLista == QTD_ESTADOS_LISTA) {
            // Se jogou todos os estados da lista, reordenar a lista e começar a mostrar novamente
            gerenciador.setListaEstados(ListUtils.shuffleList(gerenciador.getListaEstados()));
            posicaoLista = 0;
        }
        estado = gerenciador.getListaEstados().get(posicaoLista);
        String nomeImgMapa = estado.getNomeImgMapa();
        int resId = DrawableUtils.getImageIdByName(nomeImgMapa, this);
        imgEstado.setImageResource(resId);
        posicaoLista++;
    }

    public void confirmarResposta(View view) {
        String resposta = edtResposta.getText().toString().trim();
        if (resposta.isEmpty())
            Toast.makeText(this, "Campo de resposta vazio", Toast.LENGTH_SHORT).show();
        else if (gerenciador.confirmaJogada(resposta, estado.getNome())) {
            Toast.makeText(this,"Resposta correta!", Toast.LENGTH_SHORT).show();
            edtResposta.setText("");
            setMapaOnScreen();
        } else
            Toast.makeText(this,"Resposta incorreta!", Toast.LENGTH_SHORT).show();
    }

    public void pularEstado(View view) {
        setMapaOnScreen();
    }

    public void getDicaDescricao(View view) {
        DFDescricao dfDescricao = DFDescricao.newInstance(estado.getDescricao());
        dfDescricao.show(getSupportFragmentManager(), "TAG");
    }

    public void getDicaBandeira(View view) {
        DFBandeira dfBandeira = DFBandeira.newInstance(estado.getNomeImgBandeira());
        dfBandeira.show(getSupportFragmentManager(), "TAG");
    }

    public void getDicaLetra(View view) {
        DFLetra dfLetra = DFLetra.newInstance(estado.getNome());
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
