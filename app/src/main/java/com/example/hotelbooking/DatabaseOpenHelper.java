package com.example.hotelbooking;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String NAME = "RoomDB";
    private static final int DATABASE_VERSION = 8;


    public DatabaseOpenHelper(Context context, String NAME) {
        super(context, NAME, null, DATABASE_VERSION);
    }
}
