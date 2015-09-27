package br.com.lfpmobile.qualoestado.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by liliani on 27/09/15.
 */
public class MessageBox {

    public static void showInfo(Context context, String title, String msg) {
        show(context, title, msg, android.R.drawable.ic_dialog_info);
    }

    public static void showAlert(Context context, String title, String msg) {
        show(context, title, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void show(Context context, String title, String msg) {
        show(context, title, msg, 0);
    }

    public static void show(Context context, String title, String msg, int iconId) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setPositiveButton("OK", null);
        dlg.show();
    }
}
