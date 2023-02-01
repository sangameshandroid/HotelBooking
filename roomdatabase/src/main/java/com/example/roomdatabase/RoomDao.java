package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert
    void insert(RoomData roomdata);

    @Update
    void update(RoomData roomData);

    @Query("delete from RoomData where id=:id")
    void delete(int id);

    @Query("select * from RoomData")
    List<RoomData> getAllData();
}
