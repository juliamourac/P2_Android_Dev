package com.example.juliamourac.p2_android_dev.network.api;

import com.example.juliamourac.p2_android_dev.entities.ListaAcoesEntity;
import com.example.juliamourac.p2_android_dev.network.service.P2Service;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class P2Api {
    private static P2Api instance;
    private P2Service p2Service;

    public static P2Api getInstance(){
        if(instance == null){
            instance = new P2Api();
        }

        return instance;
    }

    private P2Api(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/s/50vmlj7dhfaibpj/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.p2Service = retrofit.create(P2Service.class);

    }

    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<ListaAcoesEntity> getAcoesLista(){
        return p2Service.getAcoesLista();
    }

}
