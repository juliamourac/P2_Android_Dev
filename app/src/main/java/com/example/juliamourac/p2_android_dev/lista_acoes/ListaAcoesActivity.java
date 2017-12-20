package com.example.juliamourac.p2_android_dev.lista_acoes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.juliamourac.p2_android_dev.R;
import com.example.juliamourac.p2_android_dev.entities.AcaoSocialEntity;
import com.example.juliamourac.p2_android_dev.entities.ListaAcoesEntity;
import com.example.juliamourac.p2_android_dev.mostra_detalhe_acao.MostraDetalhesAcoesActivity;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaAcoesActivity extends AppCompatActivity implements ListaAcoesView {

    @BindView(R.id.rv_acoes)
    RecyclerView rvAcoes;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    ListaAcoesPresenter listaAcoesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_acoes);
        ButterKnife.bind(this);

        listaAcoesPresenter = new ListaAcoesPresenter(this);
        listaAcoesPresenter.criaListaAdapter();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Apoie quem faz a diferença :D");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void defineDetalhe(final List<AcaoSocialEntity> listaDeAcoes) {

        AcoesAdapter acoesAdapter = new AcoesAdapter(listaDeAcoes, this);
        acoesAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (ListaAcoesActivity.this,
                                MostraDetalhesAcoesActivity.class);

                intent.putExtra("Name", listaDeAcoes.get(position).getName());
                intent.putExtra("Image", listaDeAcoes.get(position).getImage());
                intent.putExtra("Description", listaDeAcoes.get(position).getDescription());
                intent.putExtra("Site", listaDeAcoes.get(position).getSite());
                startActivity(intent);
            }
        });

        rvAcoes.setAdapter(acoesAdapter);

        // criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvAcoes.setLayoutManager(layoutManager);
        rvAcoes.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void mostraCarregamento() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void escondeCarregamento() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void salvaSharedPreferences(String jsonAcaoEntity) {
        SharedPreferences.Editor editor =
                getSharedPreferences("json_acaosocial", MODE_PRIVATE).edit();

        editor.putString("entity_acaosocial_json", jsonAcaoEntity);

        editor.apply();

        showMessage("Informações salvas com sucesso");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_download:
                listaAcoesPresenter.salvaAcoes();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void dadosOffline() {
        SharedPreferences preferences = getSharedPreferences("json_acaosocial", MODE_PRIVATE);
        String jsonAcaoSocial = preferences.getString("entity_acaosocial_json", null);

        //Caso não tenha nenhum dado salvo
        if (jsonAcaoSocial != null) {
            ListaAcoesEntity acoesListEntity = new Gson().fromJson(jsonAcaoSocial, ListaAcoesEntity.class);
            List<AcaoSocialEntity> acoesList = acoesListEntity.getAcoesLista();
            defineDetalhe(acoesList);
        }
    }
}
