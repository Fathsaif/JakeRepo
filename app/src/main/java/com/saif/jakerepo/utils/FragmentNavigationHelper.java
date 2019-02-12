package com.saif.jakerepo.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.saif.jakerepo.R;


public class FragmentNavigationHelper {

    public static void navigateToFragment(Activity context, Fragment fragment, String tag, boolean isStack,
                                          boolean isSlideAnimation,
                                          boolean existBefore) {
        FragmentActivity activity = (FragmentActivity) context;
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();

        if (!existBefore) {
            //if (!currentFragment.equals(tag)) {
            if (!isSlideAnimation)
                ft.setCustomAnimations(R.anim.right_enter, R.anim.left_out, R.anim.left_enter, R.anim.right_out);
            else {
                ft.setCustomAnimations(R.anim.left_enter, R.anim.right_out);
            }

            if (isStack) {
                ft.addToBackStack(tag);
                ft.add(R.id.fragment_container, fragment, tag);
            } else ft.replace(R.id.fragment_container, fragment, tag);
            ft.commit();
        }
        //}
    }
}
