package com.example.hotelbookingformadmin;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomData.class}, version = 8)
public abstract class RoomDB extends RoomDatabase {
    public abstract RoomDao getDao();

    public static RoomDB INSTANCE;
    public static RoomDB getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, RoomDB.class, "RoomDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
