package com.thesis.trafficinfo.Models;

public class drivers {
    String id,ref_no,type_of_app,dctb_serial,type_of_license,
            velicle_class,name,user_photo,fathers_name,mothers_name,
            spouse_name,dob,card_delivery_date,address,ref_date,
            application,dctb_date,issue_authority,NID_No,ref_expiry_date,username;


    public drivers(String id, String ref_no, String type_of_app, String dctb_serial, String type_of_license, String velicle_class, String name, String user_photo, String fathers_name, String mothers_name, String spouse_name, String dob, String card_delivery_date, String address, String ref_date, String application, String dctb_date, String issue_authority) {
        this.id = id;
        this.ref_no = ref_no;
        this.type_of_app = type_of_app;
        this.dctb_serial = dctb_serial;
        this.type_of_license = type_of_license;
        this.velicle_class = velicle_class;
        this.name = name;
        this.user_photo = user_photo;
        this.fathers_name = fathers_name;
        this.mothers_name = mothers_name;
        this.spouse_name = spouse_name;
        this.dob = dob;
        this.card_delivery_date = card_delivery_date;
        this.address = address;
        this.ref_date = ref_date;
        this.application = application;
        this.dctb_date = dctb_date;
        this.issue_authority = issue_authority;
    }

    public drivers(String id, String ref_no, String type_of_app, String dctb_serial, String type_of_license, String velicle_class, String name, String user_photo, String fathers_name, String mothers_name, String spouse_name, String dob, String card_delivery_date, String address, String ref_date, String application, String dctb_date, String issue_authority,String NID_No) {
        this.id = id;
        this.ref_no = ref_no;
        this.type_of_app = type_of_app;
        this.dctb_serial = dctb_serial;
        this.type_of_license = type_of_license;
        this.velicle_class = velicle_class;
        this.name = name;
        this.user_photo = user_photo;
        this.fathers_name = fathers_name;
        this.mothers_name = mothers_name;
        this.spouse_name = spouse_name;
        this.dob = dob;
        this.card_delivery_date = card_delivery_date;
        this.address = address;
        this.ref_date = ref_date;
        this.application = application;
        this.dctb_date = dctb_date;
        this.issue_authority = issue_authority;
        this.NID_No=NID_No;
    }
    public drivers(String id, String ref_no, String type_of_app, String dctb_serial, String type_of_license, String velicle_class, String name, String user_photo, String fathers_name, String mothers_name, String spouse_name, String dob, String card_delivery_date, String address, String ref_date, String application, String dctb_date, String issue_authority,String NID_No,String ref_expiry_date) {
        this.id = id;
        this.ref_no = ref_no;
        this.type_of_app = type_of_app;
        this.dctb_serial = dctb_serial;
        this.type_of_license = type_of_license;
        this.velicle_class = velicle_class;
        this.name = name;
        this.user_photo = user_photo;
        this.fathers_name = fathers_name;
        this.mothers_name = mothers_name;
        this.spouse_name = spouse_name;
        this.dob = dob;
        this.card_delivery_date = card_delivery_date;
        this.address = address;
        this.ref_date = ref_date;
        this.application = application;
        this.dctb_date = dctb_date;
        this.issue_authority = issue_authority;
        this.NID_No=NID_No;
        this.ref_expiry_date=ref_expiry_date;
    }
    public drivers(String id, String ref_no, String type_of_app, String dctb_serial, String type_of_license, String velicle_class, String name, String user_photo, String fathers_name, String mothers_name, String spouse_name, String dob, String card_delivery_date, String address, String ref_date, String application, String dctb_date, String issue_authority,String NID_No,String ref_expiry_date,String username) {
        this.id = id;
        this.ref_no = ref_no;
        this.type_of_app = type_of_app;
        this.dctb_serial = dctb_serial;
        this.type_of_license = type_of_license;
        this.velicle_class = velicle_class;
        this.name = name;
        this.user_photo = user_photo;
        this.fathers_name = fathers_name;
        this.mothers_name = mothers_name;
        this.spouse_name = spouse_name;
        this.dob = dob;
        this.card_delivery_date = card_delivery_date;
        this.address = address;
        this.ref_date = ref_date;
        this.application = application;
        this.dctb_date = dctb_date;
        this.issue_authority = issue_authority;
        this.NID_No=NID_No;
        this.ref_expiry_date=ref_expiry_date;
        this.username=username;
    }

    public String getNID_No() {
        return NID_No;
    }

    public void setNID_No(String NID_No) {
        this.NID_No = NID_No;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef_no() {
        return ref_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    public String getType_of_app() {
        return type_of_app;
    }

    public void setType_of_app(String type_of_app) {
        this.type_of_app = type_of_app;
    }

    public String getDctb_serial() {
        return dctb_serial;
    }

    public void setDctb_serial(String dctb_serial) {
        this.dctb_serial = dctb_serial;
    }

    public String getType_of_license() {
        return type_of_license;
    }

    public void setType_of_license(String type_of_license) {
        this.type_of_license = type_of_license;
    }

    public String getVelicle_class() {
        return velicle_class;
    }

    public void setVelicle_class(String velicle_class) {
        this.velicle_class = velicle_class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getFathers_name() {
        return fathers_name;
    }

    public void setFathers_name(String fathers_name) {
        this.fathers_name = fathers_name;
    }

    public String getRef_expiry_date() {
        return ref_expiry_date;
    }

    public void setRef_expiry_date(String ref_expiry_date) {
        this.ref_expiry_date = ref_expiry_date;
    }

    public String getMothers_name() {
        return mothers_name;
    }

    public void setMothers_name(String mothers_name) {
        this.mothers_name = mothers_name;
    }

    public String getSpouse_name() {
        return spouse_name;
    }

    public void setSpouse_name(String spouse_name) {
        this.spouse_name = spouse_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCard_delivery_date() {
        return card_delivery_date;
    }

    public void setCard_delivery_date(String card_delivery_date) {
        this.card_delivery_date = card_delivery_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRef_date() {
        return ref_date;
    }

    public void setRef_date(String ref_date) {
        this.ref_date = ref_date;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getDctb_date() {
        return dctb_date;
    }

    public void setDctb_date(String dctb_date) {
        this.dctb_date = dctb_date;
    }

    public String getIssue_authority() {
        return issue_authority;
    }

    public void setIssue_authority(String issue_authority) {
        this.issue_authority = issue_authority;
    }
}
