package com.example.juliamourac.p2_android_dev.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaAcoesEntity {

    @SerializedName("acoes_sociais")
    @Expose
    private List<AcaoSocialEntity> acoesLista;

    public List<AcaoSocialEntity> getAcoesLista() {
        return acoesLista;
    }
}
