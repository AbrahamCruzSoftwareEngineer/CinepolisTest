package com.example.abecruz.cinepolistestia.Complejo.tools;

import com.example.abecruz.cinepolistestia.Complejo.ComAPIService;
import com.example.abecruz.cinepolistestia.Complejo.controller.ComRetrofitClient;

/**
 * Created by albertocruz on 29/09/17.
 */

public class ComApiUtils {
    private ComApiUtils() {}
    public static final String BASE_URL = "https://api-stage.cinepolis.com/";
    public static ComAPIService getAPIService() {
        return ComRetrofitClient.getClient(BASE_URL).create(ComAPIService.class);
    }
}
