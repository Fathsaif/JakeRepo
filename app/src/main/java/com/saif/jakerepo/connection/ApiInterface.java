package com.saif.jakerepo.connection;

import com.saif.jakerepo.models.Repos;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("repos?")
    Observable<ArrayList<Repos>> getData(
            @Query("page") int  pageNo,
            @Query("per_page") int  perPage
    );

}

