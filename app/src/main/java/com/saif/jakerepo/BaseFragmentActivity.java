package com.saif.jakerepo;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.saif.jakerepo.utils.FragmentNavigationHelper;


public abstract class BaseFragmentActivity extends BaseActivity {


    private Toolbar toolbar;

    private ConstraintLayout layoutToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        layoutToolbar =  findViewById(R.id.toolbar_layout);

        if (savedInstanceState == null) {
            FragmentNavigationHelper.navigateToFragment(this, getFragment(), getFragment().getClass().getName(), true, true, false);
        }
        setToolBar(getToolbarTitle());
    }

    protected abstract Fragment getFragment();

    protected Fragment getAlreadyAddedFragment() {
        return getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager()
                .getBackStackEntryCount() <= 1)
            finish();
        else
            super.onBackPressed();
    }

    protected abstract int getToolbarTitle();


    private void setToolBar(int title) {

        if (title != 0) {
            toolbar = findViewById(R.id.toolbar);
            layoutToolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            ((TextView) findViewById(R.id.toolbarTitle)).setText(title);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
}
