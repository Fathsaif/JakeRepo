package com.saif.jakerepo;

import android.support.v4.app.Fragment;
import android.view.View;

import com.saif.jakerepo.ui.RepoFragment;


public class MainActivity extends BaseFragmentActivity {

    @Override
    protected View getView() {
        return null;
    }

    @Override
    protected Fragment getFragment() {
        return new RepoFragment();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.app_name;
    }
}
