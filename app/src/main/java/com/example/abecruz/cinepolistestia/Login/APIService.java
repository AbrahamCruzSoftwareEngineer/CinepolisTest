package com.example.abecruz.cinepolistestia.Login;

import com.example.abecruz.cinepolistestia.Login.model.ApiPostCall;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

/**
 * Created by albertocruz on 27/09/17.
 */


public interface APIService {
    @FormUrlEncoded
    @Headers("api_key: 199e2ce46ac525fddf")
    @POST("v2/oauth/token")
    Call<ApiPostCall> savePost(@Field("country_code")  String countryCode,
                               @Field("username")      String user,
                               @Field("password")      String password,
                               @Field("grant_type")    String grantType,
                               @Field("client_id")     String clientId,
                               @Field("client_secret") String clientSecret

    );

}
