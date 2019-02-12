package com.saif.jakerepo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.saif.jakerepo.utils.DialogUtil;


public abstract class BaseActivity extends AppCompatActivity implements ParentView {
    private ProgressDialog progressDialog;
    DialogInterface.OnClickListener ClickListener;

    private void showProgressDialog() {
        progressDialog = DialogUtil.showProgressDialog(this, R.string.text_loading, false);
    }


    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void showSnackbar(View view, int text) {
        Snackbar snack = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG);
        View snackView = snack.getView();
        snackView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        snack.show();
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }


    @Override
    public void showError() {
        showSnackbar(getView(), R.string.error_occured);
    }

    @Override
    public void showNoConnection() {
        showSnackbar(getView(), R.string.error_connection);
    }


    protected abstract View getView();
}
