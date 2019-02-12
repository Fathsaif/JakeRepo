package com.saif.jakerepo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.saif.jakerepo.RepoApplication;


public class NetworkUtil {


    private static ConnectivityManager connectivityManager() {
        return (ConnectivityManager) RepoApplication.get().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) RepoApplication.get().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return !(activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting());

    }

}
