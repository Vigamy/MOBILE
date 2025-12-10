package com.apisecurity;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("processing_url")
    User createUser(User user);

    @POST("login")
    User login(User user);
}
