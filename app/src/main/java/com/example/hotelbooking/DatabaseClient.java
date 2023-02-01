package com.example.hotelbooking;

import android.content.Context;

import androidx.room.Room;

import com.example.roomdatabase.RoomDB;

public class DatabaseClient {
    private static RoomDB DBC;

    public static RoomDB getInstance(Context context){
        if(DBC == null){
            DBC = Room.databaseBuilder(context, RoomDB.class, "RoomDB")
                    .build();
        }
        return DBC;

    }
}
