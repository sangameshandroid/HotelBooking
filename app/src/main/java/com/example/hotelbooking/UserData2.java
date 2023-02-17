package com.example.hotelbooking;

public class UserData2 {
    String fname;
    String lname;
    String email;
    String mob;
    String address;
    String room;
    String checkin;
    String checkout;
    String netpay;

    public UserData2 (String fname, String lname, String email, String mob, String address, String room, String checkin, String checkout, String netpay){
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.mob=mob;
        this.address=address;
        this.room=room;
        this.checkin=checkin;
        this.checkout=checkout;
        this.netpay=netpay;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getNetpay() {
        return netpay;
    }

    public void setNetpay(String netpay) {
        this.netpay = netpay;
    }
}
