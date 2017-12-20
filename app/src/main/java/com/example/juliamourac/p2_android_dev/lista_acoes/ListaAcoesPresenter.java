package com.example.juliamourac.p2_android_dev.lista_acoes;

import com.example.juliamourac.p2_android_dev.entities.AcaoSocialEntity;
import com.example.juliamourac.p2_android_dev.entities.ListaAcoesEntity;
import com.example.juliamourac.p2_android_dev.network.api.P2Api;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAcoesPresenter {
    private ListaAcoesView listaAcoesView;
    private List<AcaoSocialEntity> listaAcoes = new ArrayList<>();
    ListaAcoesEntity listaAcoesEntity;

    ListaAcoesPresenter(ListaAcoesView listaAcoesView){
        this.listaAcoesView = listaAcoesView;
    }

    void criaListaAdapter(){
        final P2Api p2Api = P2Api.getInstance();
        listaAcoesView.mostraCarregamento();
        p2Api.getAcoesLista().enqueue(new Callback<ListaAcoesEntity>() {
            @Override
            public void onResponse(Call<ListaAcoesEntity> call, Response<ListaAcoesEntity> response) {
                listaAcoesEntity = response.body();
                if(listaAcoesEntity != null && listaAcoesEntity.getAcoesLista() != null){
                    listaAcoesView.defineDetalhe(listaAcoesEntity.getAcoesLista());
                } else{
                    listaAcoesView.showMessage("Falha na recuperação de dados");
                }
                listaAcoesView.escondeCarregamento();
            }

            @Override
            public void onFailure(Call<ListaAcoesEntity> call, Throwable t) {
                listaAcoesView.escondeCarregamento();
                listaAcoesView.showMessage("Falha no acesso ao servidor");
                listaAcoesView.dadosOffline();
            }

        });
    }

    public void salvaAcoes() {
        String jsonAcaoEntity = new Gson().toJson(listaAcoesEntity);
        listaAcoesView.salvaSharedPreferences(jsonAcaoEntity);
    }
}
