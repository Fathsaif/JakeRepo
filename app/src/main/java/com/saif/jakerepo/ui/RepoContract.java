package com.saif.jakerepo.ui;


import com.saif.jakerepo.BasePresenter;
import com.saif.jakerepo.BaseView;
import com.saif.jakerepo.models.Repos;

import java.util.ArrayList;

interface RepoContract {

    interface JakeView extends BaseView<Presenter> {

        void showData(ArrayList<Repos> photos);
        void showNoData();
        void hideRefresh();

    }

    interface Presenter extends BasePresenter<JakeView> {

        void getData(int pageNo,int perPage);

    }
}
