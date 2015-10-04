package br.com.luizfp.qualoestado.app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import br.com.luizfp.qualoestado.R;

/**
 * Created by luiz on 10/3/15.
 */
public class BackgroundSoundService extends Service {

    private static final String TAG = "qualoestado";
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
        mediaPlayer = mediaPlayer.create(this, R.raw.background_1);
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
