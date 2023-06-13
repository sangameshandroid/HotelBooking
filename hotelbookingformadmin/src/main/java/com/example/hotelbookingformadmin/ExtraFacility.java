package com.example.hotelbookingformadmin;

public class ExtraFacility {
    String Key;
    String extrafacility;
    String extracharge;

    public ExtraFacility (String extrafacility, String extracharge){
        this.extrafacility=extrafacility;
        this.extracharge=extracharge;
    }
    public ExtraFacility(){

    }
    public ExtraFacility(String Key){
        this.Key=Key;

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getExtrafacility() {
        return extrafacility;
    }

    public void setExtrafacility(String extrafacility) {
        this.extrafacility = extrafacility;
    }

    public String getExtracharge() {
        return extracharge;
    }

    public void setExtracharge(String extracharge) {
        this.extracharge = extracharge;
    }
}
