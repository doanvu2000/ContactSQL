package com.example.myapplication;

public class Contact {
    int imgAvatar;
    String name;
    String phoneNumber;

    public Contact(int imgAvatar, String name, String phoneNumber) {
        this.imgAvatar = imgAvatar;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
