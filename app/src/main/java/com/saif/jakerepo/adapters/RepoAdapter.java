package com.saif.jakerepo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.saif.jakerepo.R;
import com.saif.jakerepo.models.Repos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoAdapter extends RecyclerView.Adapter{
    private final Context mContext;
    private final ArrayList<Repos> repos;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;


    public RepoAdapter(Context mContext, ArrayList<Repos> repos) {
        this.mContext = mContext;
        this.repos = repos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new viewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false));
            case VIEW_TYPE_LOADING:
                return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof viewHolder) {
            Repos repo = repos.get(position);
            ((viewHolder) holder).txtRepoTitle.setText(repo.getName());
            ((viewHolder) holder).txtRepoDescription.setText(repo.getDescription());
            String image = repo.getOwner().getAvatarUrl();
            RequestOptions requestOptions = new RequestOptions();
            // requestOptions.placeholder(R.drawable.image_loader);
            if (image != null) {
                Glide.with(mContext)
                        .load(image).apply(requestOptions).into(((viewHolder) holder).imgOwner);
            }
        }else {
        }
    }

    @Override
    public int getItemCount() {
        return   repos.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_repo_title)TextView txtRepoTitle;
        @BindView(R.id.img_owner)
        ImageView imgOwner;
        @BindView(R.id.txt_repo_description)
        TextView txtRepoDescription;
        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    public class FooterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progress_bar)
        ProgressBar mProgressBar;

        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == repos.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    public void add(Repos response) {
        repos.add(response);
        notifyItemInserted(repos.size() - 1);
    }

    public void addAll(List<Repos> postItems) {
        for (Repos response : postItems) {
            add(response);
        }
    }


    private void remove(Repos postItems) {
        int position = repos.indexOf(postItems);
        if (position > -1) {
            repos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new Repos());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = repos.size() - 1;
        Repos item = getItem(position);
        if (item != null) {
            repos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    Repos getItem(int position) {
        return repos.get(position);
    }
}

