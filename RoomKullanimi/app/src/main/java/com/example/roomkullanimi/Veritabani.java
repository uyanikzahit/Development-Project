package com.example.roomkullanimi;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Kisiler.class},version = 1)
public abstract class Veritabani extends RoomDatabase {

    public abstract KisilerDao getKisilerDao();

    private static Veritabani INSTANCE;

    public static Veritabani veritabaniErisim (Context context){
        if(INSTANCE == null){
            synchronized (Veritabani.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),Veritabani.class,"rehber.db").createFromAsset("rehber.db").build();

            }
        }
        return INSTANCE;
    }
}
