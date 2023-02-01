package com.example.hotelbookingformadmin;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PromoData.class}, version = 13)
public abstract class PromoDB extends RoomDatabase {
    public abstract PromoDao getPromoDao();

    public static PromoDB INSTANCE;

    public static PromoDB getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, PromoDB.class,"PromoDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}
