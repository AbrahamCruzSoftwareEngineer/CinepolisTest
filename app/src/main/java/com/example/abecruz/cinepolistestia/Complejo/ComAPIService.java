package com.example.abecruz.cinepolistestia.Complejo;

import com.example.abecruz.cinepolistestia.Complejo.model.ComApiGetCall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * Created by albertocruz on 29/09/17.
 */

public interface ComAPIService {
    @GET("v2/locations/cinemas/69?country_code=MX")
    Call<ComApiGetCall> getComplex(@Header("api_key") String apiKey);
}
