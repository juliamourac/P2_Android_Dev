package com.example.juliamourac.p2_android_dev.lista_acoes;

import com.example.juliamourac.p2_android_dev.entities.AcaoSocialEntity;

import java.util.List;

public interface ListaAcoesView {
    void defineDetalhe(List<AcaoSocialEntity> listaDeAcoes);
    void mostraCarregamento();
    void escondeCarregamento();
    void salvaSharedPreferences(String jsonAcaoEntity);
    void showMessage(String message);
    void dadosOffline();
}
