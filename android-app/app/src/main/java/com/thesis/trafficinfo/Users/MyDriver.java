package com.thesis.trafficinfo.Users;

public class MyDriver {
    String id,name,Driving_license_no,Vehicle_reg_no,user_name,phone,location,status,image,d_name;

    public MyDriver(String id, String name, String driving_license_no, String vehicle_reg_no, String user_name, String phone, String location, String status, String image) {
        this.id = id;
        this.name = name;
        Driving_license_no = driving_license_no;
        Vehicle_reg_no = vehicle_reg_no;
        this.user_name = user_name;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.image = image;
    }

    public MyDriver(String d_name,String status) {
        this.d_name = d_name;
        this.status=status;
    }

    public MyDriver(String id, String name, String driving_license_no, String vehicle_reg_no, String user_name, String phone, String location, String status, String image, String  d_name) {
        this.id = id;
        this.name = name;
        Driving_license_no = driving_license_no;
        Vehicle_reg_no = vehicle_reg_no;
        this.user_name = user_name;
        this.phone = phone;
        this.location = location;
        this.status = status;
        this.image = image;
        this.d_name=d_name;
    }

    public MyDriver(String id, String name, String driving_license_no, String vehicle_reg_no, String user_name, String phone, String location, String status) {
        this.id = id;
        this.name = name;
        Driving_license_no = driving_license_no;
        Vehicle_reg_no = vehicle_reg_no;
        this.user_name = user_name;
        this.phone = phone;
        this.location = location;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriving_license_no() {
        return Driving_license_no;
    }

    public void setDriving_license_no(String driving_license_no) {
        Driving_license_no = driving_license_no;
    }

    public String getVehicle_reg_no() {
        return Vehicle_reg_no;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVehicle_reg_no(String vehicle_reg_no) {
        Vehicle_reg_no = vehicle_reg_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
