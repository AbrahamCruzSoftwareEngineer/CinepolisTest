package com.example.abecruz.cinepolistestia.Login.tools;

import com.example.abecruz.cinepolistestia.Login.APIService;
import com.example.abecruz.cinepolistestia.Login.controller.RetrofitClient;

/**
 * Created by albertocruz on 28/09/17.
 */

public class ApiUtils {
    private ApiUtils() {}
    public static final String BASE_URL = "https://api-stage.cinepolis.com/";
    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
