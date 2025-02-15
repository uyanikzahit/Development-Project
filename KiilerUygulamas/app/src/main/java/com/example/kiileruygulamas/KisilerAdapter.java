package com.example.kiileruygulamas;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Kisiler>kisilerListe;

    private Veritabani vt;

    public KisilerAdapter(Context mContext, List<Kisiler> kisilerListe, Veritabani vt) {
        this.mContext = mContext;
        this.kisilerListe = kisilerListe;
        this.vt = vt;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        final Kisiler kisi = kisilerListe.get(position);

        holder.textViewKisiBilgi.setText(kisi.getKisi_ad()+" - "+ kisi.getKisi_tel());

        holder.imageViewNokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(),holder.imageViewNokta);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.action_sil) {
                            Snackbar.make(holder.imageViewNokta, "Kişi Silinsin Mi?", Snackbar.LENGTH_SHORT)
                                    .setAction("Evet", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            new KisilerDao().kisiSil(vt,kisi.getKisi_id());

                                            kisilerListe = new KisilerDao().tumKisiler(vt);
                                            notifyDataSetChanged();

                                        }
                                    })
                                    .show();
                            return true;
                        } else if (menuItem.getItemId() == R.id.action_guncelle) {
                            alertGoster(kisi);
                            return true;
                        }
                        return false;

                    }

                });

                popupMenu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return kisilerListe.size();
    }

    public class  CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewKisiBilgi;
        private ImageView imageViewNokta;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            textViewKisiBilgi = itemView.findViewById(R.id.textViewKisiBilgi);
            imageViewNokta = itemView.findViewById(R.id.imageViewNokta);
        }
    }

    public void alertGoster(Kisiler kisi){
        LayoutInflater layout = LayoutInflater.from(mContext);
        View tasarim = layout.inflate(R.layout.alert_tasarim,null);

        final EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        final EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        editTextAd.setText(kisi.getKisi_ad());
        editTextTel.setText(kisi.getKisi_tel());

        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("Kişi Güncelle");
        ad.setView(tasarim);
        ad.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();

                new KisilerDao().kisiGuncelle(vt,kisi.getKisi_id(),kisi_ad,kisi_tel);

                kisilerListe = new KisilerDao().tumKisiler(vt);
                notifyDataSetChanged();


            }
        });

        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        ad.create().show();

    }
}
