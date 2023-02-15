package com.example.hotelbooking;

import kotlin.text.UStringsKt;

public class RoomDatafir {
    private String room;
    private String cost;
    private String tax;


    public RoomDatafir(String room, String cost, String tax){

        this.room=room;
        this.cost=cost;
        this.tax=tax;
    }

    public RoomDatafir(){

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
        this.cost = cost;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}
