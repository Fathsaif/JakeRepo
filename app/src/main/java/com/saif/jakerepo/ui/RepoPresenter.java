package com.saif.jakerepo.ui;

import android.util.Log;

import com.saif.jakerepo.connection.RestClient;
import com.saif.jakerepo.models.Repos;
import com.saif.jakerepo.utils.NetworkUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RepoPresenter implements RepoContract.Presenter {

    private RepoContract.JakeView jakeView;

    @Override
    public void getData(int pageNo, int perPage) {
        getRepos(pageNo, perPage);
    }

    private void getRepos(int pageNo, int perPage) {
        if (pageNo==1)
        jakeView.showProgress();
        Observable<ArrayList<Repos>> observable = RestClient.getClient().getData(pageNo, perPage);
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ArrayList<Repos>, ArrayList<Repos>>() {
                    @Override
                    public ArrayList<Repos> apply(ArrayList<Repos> data) {
                        return (ArrayList<Repos>) (data);
                    }
                }).subscribe(new Observer<ArrayList<Repos>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ArrayList<Repos> repos) {
                jakeView.hideProgress();
                if (repos == null || repos.size() == 0)
                    jakeView.showNoData();
                else jakeView.showData(repos);
                //textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("error", e.getMessage());
                jakeView.hideProgress();
                jakeView.hideRefresh();
                if (NetworkUtil.isConnected()) {
                    jakeView.showNoConnection();
                } else
                    jakeView.showError();
            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    public void registerView(RepoContract.JakeView view) {
        jakeView = view;

    }

    @Override
    public void unregisterView() {

    }

    @Override
    public void start() {

    }
}
