package com.example.hotelbookingformadmin;

public class PromoDatabase {
    String Key;
    String promoid;
    String promocode;
    String promodiscount;

    public PromoDatabase(String promoid, String promocode, String promodiscount){
        this.promoid=promoid;
        this.promocode=promocode;
        this.promodiscount=promodiscount;
    }

    public PromoDatabase(){

    }
    public PromoDatabase(String Key){
        this.Key=Key;

    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
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
