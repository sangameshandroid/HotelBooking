package com.example.hotelbooking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ClientDBCallback extends RoomDatabase.Callback {
    private Context context;


    public ClientDBCallback(Context context) {
        this.context = context;
    }



    @SuppressLint("Range")
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        DatabaseOpenHelper assetHelper = new DatabaseOpenHelper(context, "RoomDB");
        SQLiteDatabase importedDB = assetHelper.getReadableDatabase();

        ClientDao myDao = ClientDB.getInstant(context).myDao();
        Cursor cursor = importedDB.rawQuery("select * from RoomData", null);
        while (cursor.moveToNext()){
            ClientData clientData = new ClientData(0, null, null, null);
            clientData.setId(cursor.getInt(cursor.getColumnIndex("id")));
            clientData.setRoom(cursor.getString(cursor.getColumnIndex("room")));
            clientData.setCost(cursor.getString(cursor.getColumnIndex("cost")));
            clientData.setTax(cursor.getString(cursor.getColumnIndex("tax")));
            myDao.Insert(clientData);
        }
        cursor.close();
      assetHelper.close();
    }

}
