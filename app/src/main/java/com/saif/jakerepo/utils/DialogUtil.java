package com.saif.jakerepo.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.saif.jakerepo.R;


public final class DialogUtil {


    private DialogUtil() {
    }


    public static ProgressDialog showProgressDialog(Context context, int message, boolean cancelable) {
        ProgressDialog dialog = new ProgressDialog(context,R.style.AppCompatAlertDialogStyle);
        dialog.setMessage(context.getString(message));
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.show();
        return dialog;
    }





}
