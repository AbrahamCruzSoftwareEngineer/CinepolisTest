package com.example.abecruz.cinepolistestia.Perfil;

import com.example.abecruz.cinepolistestia.Perfil.model.ApiGetCall;
import com.example.abecruz.cinepolistestia.Perfil.model.Loyalty;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by albertocruz on 28/09/17.
 */

public interface APIService {
    @GET("v1/members/profile?country_code=MX")
    Call<ApiGetCall> getUser(@Header("api_key") String apiKey, @Header("Authorization") String authorization);

    @FormUrlEncoded
    @Headers("api_key: 199e2ce46ac525fddf")
    @POST("v1/members/loyalty")
    Call<Loyalty> saveTransaction(@Field("country_code")           String countryCode,
                                  @Field("card_number")            String cardNumber,
                                  @Field("transaction_include")    String transactionInclude
    );
}
