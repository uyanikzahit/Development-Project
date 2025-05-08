package com.example.rehberuygulamasi.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rehberuygulamasi.data.entity.Kisiler;
import com.example.rehberuygulamasi.data.repo.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {
    private KisilerDaoRepository krepo;
    public MutableLiveData<List<Kisiler>> kisilerListesi = new MutableLiveData();

    @Inject
    public AnasayfaViewModel(KisilerDaoRepository krepo){
        this.krepo = krepo;
        kisileriYukle();
        kisilerListesi = krepo.kisileriGetir();
    }
    public void ara(String aramaKelimesi){
        krepo.kisiAra(aramaKelimesi);
    }

    public void sil (int kisi_id){
        krepo.kisiSil(kisi_id);

    }

    public void kisileriYukle(){

        krepo.tumKisileriAl();
    }

}
