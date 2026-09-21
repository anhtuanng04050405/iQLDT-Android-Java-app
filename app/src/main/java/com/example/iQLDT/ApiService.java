package com.example.iQLDT;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api_post")
    Call<PostResponse> getPosts();
}