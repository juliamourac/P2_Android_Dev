package com.example.juliamourac.p2_android_dev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juliamourac.p2_android_dev.lista_acoes.ListaAcoesActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(this, ListaAcoesActivity.class);
        startActivity(intent);
        finish();
    }
}
