package com.example.juliamourac.p2_android_dev.lista_acoes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliamourac.p2_android_dev.R;
import com.example.juliamourac.p2_android_dev.entities.AcaoSocialEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class AcoesAdapter extends RecyclerView.Adapter<AcoesAdapter.ViewHolder> {

    private List<AcaoSocialEntity> listaDeAcoes;
    OnRecyclerViewSelected onRecyclerViewSelected;
    private Context context;

    AcoesAdapter(List<AcaoSocialEntity> lista, Context ctx) {

        listaDeAcoes = lista;
        context = ctx;
    }

    //infla o view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_acoes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AcaoSocialEntity acaoEntity = listaDeAcoes.get(position);
        holder.txNomeAcao.setText(acaoEntity.getName());
        Picasso.with(context)
                .load(acaoEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imgBackgroud);
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return listaDeAcoes.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_nome_acao)
        TextView txNomeAcao;

        @BindView(R.id.image_view_background)
        ImageView imgBackgroud;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null) {}
                onRecyclerViewSelected.onClick(view, getAdapterPosition());
        }
    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }

}

