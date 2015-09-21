package br.com.lfpmobile.qualoestado.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by luiz on 9/21/15.
 */
public class DrawableUtils {

    public DrawableUtils () {
    }

    public static int getImageIdByName(String mDrawableName, Context context) {
        int resID = context.getResources().getIdentifier(mDrawableName , "drawable",
                context.getPackageName());
        return resID;
    }
}
