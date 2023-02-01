package com.example.hotelbooking;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoClosingRoomOpenHelperFactory;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Companion;
import androidx.room.SQLiteCopyOpenHelperFactory;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {ClientData.class}, version = 8)
public abstract class ClientDB extends RoomDatabase {
    public abstract ClientDao myDao();

    public static ClientDB INSTANT;
    public static ClientDB getInstant(Context context){
        if(INSTANT==null){
            synchronized (ClientDB.class){
                if(INSTANT==null){
                    INSTANT = Room.databaseBuilder(context, ClientDB.class, "ClientDB")
                            .createFromAsset("database/RoomDB")
                            .addCallback(new ClientDBCallback(context))
                            .build();

                }
            }

        }
        return INSTANT;

    }
}

class MyDatabaseOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final Context context;

   public MyDatabaseOpenHelperFactory(Context context){
       this.context=context;
   }

    @NonNull
    @Override
    public SupportSQLiteOpenHelper create(@NonNull SupportSQLiteOpenHelper.Configuration configuration) {
        return (SupportSQLiteOpenHelper) new DatabaseOpenHelper(context, configuration.name);
    }
}
