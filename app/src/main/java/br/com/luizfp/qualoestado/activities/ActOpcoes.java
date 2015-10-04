package br.com.luizfp.qualoestado.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.luizfp.qualoestado.QualOEstadoApp;
import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.app.BackgroundSoundService;
import br.com.luizfp.qualoestado.util.PrefUtils;

public class ActOpcoes extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
        setupActionBar();
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
        if (!(((QualOEstadoApp)getApplication()).isTrocaActivity()))
            startService(BackgroundSoundService.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // support.v4 não aceita PreferenceFragment, por isso a lib do github.
    public static class PrefsFragment extends PreferenceFragment {

        private static final String TOGGLE_BUTTON_SOUND = "toggle_button_sound";
        private static final String TOGGLE_BG_MUSIC = "toggle_bg_music";
        private static final String LIST_BG_MUSIC = "list_bg_music";

        SwitchPreference toggleButtonSound;
        SwitchPreference toggleBgMusic;
        ListPreference listBgMusic;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            toggleButtonSound = (SwitchPreference) findPreference(TOGGLE_BUTTON_SOUND);
            toggleBgMusic = (SwitchPreference) findPreference(TOGGLE_BG_MUSIC);
            listBgMusic = (ListPreference) findPreference(LIST_BG_MUSIC);

            PrefUtils prefUtils = PrefUtils.getInstance(getActivity());

            if (!prefUtils.getButtonSound())
                toggleButtonSound.setChecked(false);

            if (!prefUtils.isBgMusicEnable()) {
                toggleBgMusic.setChecked(false);
                listBgMusic.setEnabled(false);
            }

            listBgMusic.setValueIndex(prefUtils.getBgMusic());
        }
    }
}
