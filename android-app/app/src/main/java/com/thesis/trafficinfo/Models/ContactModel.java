package com.thesis.trafficinfo.Models;

public class ContactModel {
    String id, owner_name,number,contact_name;

    public ContactModel(String id, String owner_name, String number, String contact_name) {
        this.id = id;
        this.owner_name = owner_name;
        this.number = number;
        this.contact_name = contact_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
