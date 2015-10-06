package br.com.luizfp.qualoestado.util;

import android.content.Context;

/**
 * Created by luiz on 10/5/15.
 */
public class BgMusicUtils {

    public static int getRawIdByName(String mRawName, Context context) {
        int resID = context.getResources().getIdentifier(mRawName , "raw",
                context.getPackageName());
        return resID;
    }
}
