package com.example.mvvmkullanimi;

import androidx.lifecycle.MutableLiveData;

public class MatematikRepository {
    private MutableLiveData<String> MatematikselSonuc;

    public MatematikRepository (){
        MatematikselSonuc = new MutableLiveData<String >("0");
    }

    public MutableLiveData<String > getMatematikselSonuc() {
        return MatematikselSonuc;
    }

    public void toplama(String alinanSayi1,String alinanSayi2){




        int sayi1 = Integer.parseInt(alinanSayi1);
        int sayi2 = Integer.parseInt(alinanSayi2);

        int toplam = sayi1 + sayi2;
        MatematikselSonuc.setValue(String.valueOf(toplam));



    }

    public void carpma(String alinanSayi1,String alinanSayi2){



        int sayi1 = Integer.parseInt(alinanSayi1);
        int sayi2 = Integer.parseInt(alinanSayi2);

        int carpma = sayi1 * sayi2;
        MatematikselSonuc.setValue(String.valueOf(carpma));


    }
}
