package com.github.githubapidemo.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("search/repositories")
    Call<DataBean> getData(@Query("q") String query);

}
