package com.example.juliamourac.p2_android_dev.mostra_detalhe_acao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliamourac.p2_android_dev.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MostraDetalhesAcoesActivity extends AppCompatActivity {

    @BindView(R.id.text_view_descricao)
    TextView descricaoAcao;
    @BindView(R.id.text_view_site)
    TextView siteAcao;
    @BindView(R.id.image_view_header)
    ImageView headerAcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_acoes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String image = intent.getStringExtra("Image");
        String descricao = intent.getStringExtra("Description");
        String site = intent.getStringExtra("Site");

        descricaoAcao.setText(descricao);
        siteAcao.setText(site);
        Picasso.with(getBaseContext())
                .load(image)
                .centerCrop()
                .fit()
                .into(headerAcao);

        //Altera o titulo da action bar ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

