package com.example.hotelbooking;

public class UserData {
    String roomtype;
    String checkin;
    String chechout;
    String netpayable;

    public UserData(String roomtype, String checkin, String chechout, String netpayable){
        this.roomtype=roomtype;
        this.checkin=checkin;
        this.chechout=chechout;
        this.netpayable=netpayable;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getChechout() {
        return chechout;
    }

    public void setChechout(String chechout) {
        this.chechout = chechout;
    }

    public String getNetpayable() {
        return netpayable;
    }

    public void setNetpayable(String netpayable) {
        this.netpayable = netpayable;
    }
}
