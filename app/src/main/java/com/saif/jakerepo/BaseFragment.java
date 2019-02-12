package com.saif.jakerepo;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.saif.jakerepo.utils.DialogUtil;

public class BaseFragment extends Fragment implements ParentView {
    private ProgressDialog progressDialog;

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
        Toast.makeText(getContext(),getString(R.string.error_connection),Toast.LENGTH_LONG).show();
    }


    private void showProgressDialog() {
        progressDialog = DialogUtil.showProgressDialog(getActivity(), R.string.text_loading, false);
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    private void showSnackbar(View view, int text) {
        Snackbar snack = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG);
        View snackView = snack.getView();
        snackView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        snack.show();
    }
}
