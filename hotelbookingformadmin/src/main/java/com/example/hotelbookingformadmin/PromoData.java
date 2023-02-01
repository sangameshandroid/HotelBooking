package com.example.hotelbookingformadmin;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PromoData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String promoid;

    private String promocode;

    private String promodiscount;

    public PromoData(int id, String promoid, String promocode, String promodiscount){
        this.id=id;
        this.promoid=promoid;
        this.promocode=promocode;
        this.promodiscount=promodiscount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoid() {
        return promoid;
    }

    public void setPromoid(String promoid) {
        this.promoid = promoid;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getPromodiscount() {
        return promodiscount;
    }

    public void setPromodiscount(String promodiscount) {
        this.promodiscount = promodiscount;
    }
}
