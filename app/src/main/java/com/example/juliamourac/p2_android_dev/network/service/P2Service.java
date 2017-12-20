package com.example.juliamourac.p2_android_dev.network.service;

import com.example.juliamourac.p2_android_dev.entities.ListaAcoesEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface P2Service {

    @GET("sociais.json")
    Call<ListaAcoesEntity> getAcoesLista();
}
