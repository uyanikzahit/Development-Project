package com.example.developmentproject;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class YeniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yeni);

      Kisiler gelenKisi = (Kisiler) getIntent().getSerializableExtra("nesne");

        Log.e("Gelen Tcno",String.valueOf(gelenKisi.getTcno()));
        Log.e("Gelen İsim",gelenKisi.getIsim());
        Log.e("Gelen Boy",String.valueOf(gelenKisi.getBoy()));


    }
}