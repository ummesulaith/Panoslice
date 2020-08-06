package com.example.panoslice.data.remote;

import com.example.panoslice.data.model.GitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitCall {
    @GET("repositories?")
    Call<GitModel> getRepoDetails(@Query("q") String repoName);
}
