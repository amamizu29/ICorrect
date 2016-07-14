package com.dkstudio.icorrect.practice.speaking.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Web services builder
 */
public class ServiceBuilder
{
    public static final String BASE_URL = "http://prosoftforlife.com/";
    private static Retrofit sInstance;
    private static SpeakingService sService;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sInstance;
    }

    public static SpeakingService getService() {
        if (sService == null) {
            sService = getRetrofit().create(SpeakingService.class);
        }

        return sService;
    }
}
