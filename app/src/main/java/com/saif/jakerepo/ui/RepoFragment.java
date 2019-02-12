package com.saif.jakerepo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.saif.jakerepo.BaseFragment;
import com.saif.jakerepo.R;
import com.saif.jakerepo.adapters.RepoAdapter;
import com.saif.jakerepo.constants.BundleConstants;
import com.saif.jakerepo.models.Repos;
import com.saif.jakerepo.utils.PaginationScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoFragment extends BaseFragment implements RepoContract.JakeView, SwipeRefreshLayout.OnRefreshListener {


    private RepoContract.Presenter mpresenter;
    @BindView(R.id.txt_no_data)
    TextView txtNoData;
    @BindView(R.id.rv_repos)
    RecyclerView rvRepos;
    @BindView(R.id.swipe_layout_refresh_done)
    SwipeRefreshLayout swipeLayoutRefreshDone;
    RepoAdapter repoAdapter;
    //constant for start page
    public static final int PAGE_START = 1;
    public static final int PAGE_SIZE = 15;
    private int currentPage = PAGE_START;
    //check for the last page to hide progress and no more request.
    private boolean isLastPage = false;
    private int totalPage = 10;
    //to determint the progress is visible and waiting for new data
    private boolean isLoading = false;
    int itemCount = 0;
    LinearLayoutManager layoutManager;

    public RepoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        swipeLayoutRefreshDone.setOnRefreshListener(this);
        init();
        return view;
    }

    private void init() {
        layoutManager = new LinearLayoutManager(getActivity());
        rvRepos.setLayoutManager(layoutManager);
        //intialize presenter and register view to it
        mpresenter = new RepoPresenter();
        mpresenter.registerView(this);
        //get data from api
        mpresenter.getData(currentPage,PAGE_SIZE);

        repoAdapter = new RepoAdapter(getActivity(),new ArrayList<Repos>());
        rvRepos.setAdapter(repoAdapter);
        //recycelr view listener to determine the third item from bottom.
        rvRepos.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                //request more data with new page count
                isLoading = true;
                currentPage++;
                mpresenter.getData(currentPage, PAGE_SIZE);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }


    @Override
    public void showData(ArrayList<Repos> repos) {

        if (currentPage != PAGE_START) repoAdapter.removeLoading();
        repoAdapter.addAll(repos);
        swipeLayoutRefreshDone.setRefreshing(false);
        if (repos.size()>0) repoAdapter.addLoading();
        else {
            isLastPage = true;
        }
        isLoading = false;


    }

    @Override
    public void showNoData() {
        //show no data when it is empty.
        if (currentPage==1)txtNoData.setVisibility(View.VISIBLE);
        repoAdapter.removeLoading();
    }

    @Override
    public void hideRefresh() {
        swipeLayoutRefreshDone.setRefreshing(false);
    }

    @Override
    public void setPresenter(RepoContract.Presenter presenter) {
        this.mpresenter = presenter;
    }

    @Override
    public void onRefresh() {
        currentPage = PAGE_START;
        isLastPage = false;
        repoAdapter.clear();
        mpresenter.getData(currentPage,PAGE_SIZE);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(BundleConstants.CACHED, "cached");
        setRetainInstance(true);
    }


}
