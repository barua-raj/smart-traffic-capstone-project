package com.thesis.trafficinfo.Models;

public class NID_model {
    String id,Name,Father,Mother,Date_of_birth,NID_No,photo,
            Address,Blood_group,Issue_Date,Place_of_Birth;

    public NID_model(String id, String name, String father, String mother, String date_of_birth, String NID_No, String photo, String address, String blood_group, String issue_Date, String place_of_Birth) {
        this.id = id;
        Name = name;
        Father = father;
        Mother = mother;
        Date_of_birth = date_of_birth;
        this.NID_No = NID_No;
        this.photo = photo;
        Address = address;
        Blood_group = blood_group;
        Issue_Date = issue_Date;
        Place_of_Birth = place_of_Birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFather() {
        return Father;
    }

    public void setFather(String father) {
        Father = father;
    }

    public String getMother() {
        return Mother;
    }

    public void setMother(String mother) {
        Mother = mother;
    }

    public String getDate_of_birth() {
        return Date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        Date_of_birth = date_of_birth;
    }

    public String getNID_No() {
        return NID_No;
    }

    public void setNID_No(String NID_No) {
        this.NID_No = NID_No;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBlood_group() {
        return Blood_group;
    }

    public void setBlood_group(String blood_group) {
        Blood_group = blood_group;
    }

    public String getIssue_Date() {
        return Issue_Date;
    }

    public void setIssue_Date(String issue_Date) {
        Issue_Date = issue_Date;
    }

    public String getPlace_of_Birth() {
        return Place_of_Birth;
    }

    public void setPlace_of_Birth(String place_of_Birth) {
        Place_of_Birth = place_of_Birth;
    }
}
