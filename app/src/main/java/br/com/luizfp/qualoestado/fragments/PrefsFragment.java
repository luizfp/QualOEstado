package br.com.luizfp.qualoestado.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;

import br.com.luizfp.qualoestado.QualOEstadoApp;
import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.app.BackgroundSoundService;
import br.com.luizfp.qualoestado.util.PrefUtils;

/**
 * Created by luiz on 10/4/15.
 */
// support.v4 não aceita PreferenceFragment, por isso a lib do github. --- lib não mais
// necessária minSdk alterado.
public class PrefsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener{

    private static final String TOGGLE_BUTTON_SOUND = "toggle_button_sound";
    private static final String TOGGLE_BG_MUSIC = "toggle_bg_music";
    private static final String LIST_BG_MUSIC = "list_bg_music";
    private static final String TAG = "qualoestado";

    Context context;
    PrefUtils prefUtils;
    SwitchPreference toggleButtonSound;
    SwitchPreference toggleBgMusic;
    ListPreference listBgMusic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        context = getActivity().getApplicationContext();
        toggleButtonSound = (SwitchPreference) findPreference(TOGGLE_BUTTON_SOUND);
        toggleBgMusic = (SwitchPreference) findPreference(TOGGLE_BG_MUSIC);
        listBgMusic = (ListPreference) findPreference(LIST_BG_MUSIC);

        prefUtils = PrefUtils.getInstance(getActivity());

        if (!prefUtils.getButtonSound())
            toggleButtonSound.setChecked(false);

        if (!prefUtils.isBgMusicEnable()) {
            toggleBgMusic.setChecked(false);
            listBgMusic.setEnabled(false);
        }

        listBgMusic.setValueIndex(prefUtils.getBgMusic());

        toggleBgMusic.setOnPreferenceChangeListener(this);
        toggleButtonSound.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        prefUtils.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        prefUtils.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case LIST_BG_MUSIC:
                restartService(BackgroundSoundService.class);
                break;
            case TOGGLE_BUTTON_SOUND:
                ((QualOEstadoApp)getActivity().getApplication()).refreshPlayButtonSound();
                break;
            case TOGGLE_BG_MUSIC:
                ((QualOEstadoApp)getActivity().getApplication()).refreshPlayBgMusic();
                if (toggleBgMusic.isChecked()) {
                    listBgMusic.setEnabled(true);
                    startService(BackgroundSoundService.class);
                }
                else {
                    listBgMusic.setEnabled(false);
                    stopService(BackgroundSoundService.class);
                }
                break;
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d(TAG, "PregerenceListChange reached");

        /*
         * Parece ter um bug esquisito aqui, de lógica básica.
         */

        /*
        switch (preference.getKey()) {
            case TOGGLE_BG_MUSIC:
                if (!toggleBgMusic.isChecked()) {
                    listBgMusic.setEnabled(true);
                    startService(BackgroundSoundService.class);
                }
                else {
                    listBgMusic.setEnabled(false);
                    stopService(BackgroundSoundService.class);
                }
                break;
        }
        */
        return true;
    }

    private void restartService(Class c) {
        stopService(c);
        startService(c);
    }

    private void startService(Class c) {
        Intent intent = new Intent(context, c);
        getActivity().startService(intent);
    }

    private void stopService(Class c) {
        Intent intent = new Intent(context, c);
        getActivity().stopService(intent);
    }
}