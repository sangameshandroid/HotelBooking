package com.example.hotelbooking;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDao {
    @Insert
    void Insert(ClientData clientData);

    @Update
    void Update(ClientData clientData);

    @Query("delete from ClientData where id=:id")
    void Delete(int id);

    @Query("select * from ClientData")
    List<ClientData> getData();



}

