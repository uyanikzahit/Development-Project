package com.example.filmleruygulamasi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class FilmlerActivity extends AppCompatActivity {
    private Toolbar toolbar2;
    private RecyclerView filmlerRv;
    private ArrayList<Filmler>filmlerArrayList;
    private FilmlerAdapter adapter;
    private Kategoriler kategori;
    private Veritabani vt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filmler);

        toolbar2 = findViewById(R.id.toolbar2);
        filmlerRv = findViewById(R.id.filmlerRv);

        vt = new Veritabani(this);


        kategori = (Kategoriler) getIntent().getSerializableExtra("kategori_nesne");


        toolbar2.setTitle(kategori.getKategori_ad());
        setSupportActionBar(toolbar2);


        filmlerArrayList = new FilmlerDao().tumFilmlerByKategoriId(vt,kategori.getKategori_id());

        filmlerRv.setHasFixedSize(true);
        filmlerRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        adapter = new FilmlerAdapter(this,filmlerArrayList);
        filmlerRv.setAdapter(adapter);

    }
}