package br.com.luizfp.qualoestado.app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.luizfp.qualoestado.R;
import br.com.luizfp.qualoestado.util.BgMusicUtils;
import br.com.luizfp.qualoestado.util.PrefUtils;

/**
 * Created by luiz on 10/3/15.
 */
public class BackgroundSoundService extends Service {

    private static final String TAG = "qualoestado";
    private MediaPlayer mediaPlayer;
    private static final String MUSICA_1 = "background_1";
    private static final String MUSICA_2 = "background_2";
    private static final String MUSICA_3 = "background_3";
    private static final String MUSICA_4 = "background_4";
    private static final String PREFS_MUSICA_1 = "Música 1";
    private static final String PREFS_MUSICA_2 = "Música 2";
    private static final String PREFS_MUSICA_3 = "Música 3";
    private static final String PREFS_MUSICA_4 = "Música 4";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
        int resId;
        Log.d(TAG, "Prefs BG: " + PrefUtils.getInstance(this).getBgMusic());
        switch (PrefUtils.getInstance(this).getBgMusic()) {
            case 0:
                resId = BgMusicUtils.getRawIdByName(MUSICA_1, this);
                mediaPlayer = mediaPlayer.create(this, resId);
                break;
            case 1:
                resId = BgMusicUtils.getRawIdByName(MUSICA_2, this);
                mediaPlayer = mediaPlayer.create(this, resId);
                break;
            case 2:
                resId = BgMusicUtils.getRawIdByName(MUSICA_3, this);
                mediaPlayer = mediaPlayer.create(this, resId);
                break;
            case 3:
                resId = BgMusicUtils.getRawIdByName(MUSICA_4, this);
                mediaPlayer = mediaPlayer.create(this, resId);
                break;
        }
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        Log.d(TAG, "Media Player Started");
        if (mediaPlayer.isLooping() != true)
            Log.d(TAG, "Problem in playing audio");
        return 1;
    }

    public void onStop() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void onPause() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
