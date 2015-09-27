package br.com.lfpmobile.qualoestado.activities;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import br.com.lfpmobile.qualoestado.R;

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
}
