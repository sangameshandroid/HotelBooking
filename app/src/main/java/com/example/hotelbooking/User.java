package com.example.hotelbooking;



public class User {
    String firstname;
    String lastname;
    String gender;
    String mobile;
    String address;
    String city;
    String country;
    String documenttype;
    String document;

    public User (String firstname, String lastname, String gender, String mobile, String address, String city, String country, String documenttype, String document
                 ){
        this.firstname = firstname;
        this.lastname=lastname;
        this.gender=gender;

        this.mobile=mobile;

        this.address=address;
        this.city=city;
        this.country= country;
        this.documenttype=documenttype;
        this.document=document;

    }

    public User(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
