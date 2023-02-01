package com.example.hotelbooking;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String room;
    private String cost;
    private String tax;
    public RoomData(int id, String room, String cost, String tax){
        this.id=id;
        this.room=room;
        this.cost=cost;
        this.tax=tax;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost=cost;

    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}
