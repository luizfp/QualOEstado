package br.com.luizfp.qualoestado.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import br.com.luizfp.qualoestado.R;

/**
 * Created by liliani on 27/09/15.
 */
public class BaseActivity extends DebugActivity {

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void openActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    protected void startService(Class c) {
        Intent intent = new Intent(this, c);
        startService(intent);
    }

    protected void stopService(Class c) {
        Intent intent = new Intent(this, c);
        stopService(intent);
    }
}
