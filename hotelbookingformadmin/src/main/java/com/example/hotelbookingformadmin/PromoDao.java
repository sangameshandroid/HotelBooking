package com.example.hotelbookingformadmin;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PromoDao {
    @Insert
    void insert(PromoData promoData);

    @Update
    void update(PromoData promoData);

    @Query("delete from PromoData where id=:id")
    void delete(int id);

    @Query("select * from PromoData")
    List<PromoData> getPromoData();
}
