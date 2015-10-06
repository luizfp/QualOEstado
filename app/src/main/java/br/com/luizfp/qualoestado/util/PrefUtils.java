package br.com.luizfp.qualoestado.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by luiz on 10/4/15.
 */
public class PrefUtils {

    private static final String TOGGLE_BUTTON_SOUND = "toggle_button_sound";
    private static final String TOGGLE_BG_MUSIC = "toggle_bg_music";
    private static final String LIST_BG_MUSIC = "list_bg_music";

    private static volatile PrefUtils prefUtils;
    private static SharedPreferences sharedPreferences;

    private PrefUtils(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static synchronized PrefUtils getInstance(Context context) {
        if (prefUtils == null) {
            prefUtils = new PrefUtils(context);
        }
        return prefUtils;
    }

    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener){
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener){
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public int getBgMusic() {
        return Integer.parseInt(sharedPreferences.getString(LIST_BG_MUSIC, "0"));
    }

    public boolean getButtonSound() {
        return sharedPreferences.getBoolean(TOGGLE_BUTTON_SOUND, true);
    }

    public boolean isBgMusicEnable() {
        return sharedPreferences.getBoolean(TOGGLE_BG_MUSIC, true);
    }

}
