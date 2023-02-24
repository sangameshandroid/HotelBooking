package com.example.hotelbookingformadmin;

public class RoomTypefir {
    String Key;
    String Room;
    String Cost;
    String Tax;

    public RoomTypefir(String Room, String Cost, String Tax){
        this.Room=Room;
        this.Cost=Cost;
        this.Tax=Tax;

    }
    public RoomTypefir(){

    }

    public RoomTypefir(String Key){
        this.Key=Key;

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }
}
