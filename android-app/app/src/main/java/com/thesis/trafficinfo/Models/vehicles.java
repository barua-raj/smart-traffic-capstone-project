package com.thesis.trafficinfo.Models;

public class vehicles {
    String id,Registration_No,Vehicle_Description,Vehicle_class,CC,
            Color,Fuel,Seat,Engine_No,Chassis_No,Hire,Date,Wheel_Base,
            Issuing_Authority,Weight_Unladen,Weight_laden,Owner_name,Owner_address,Mfg_Year,NID_No,photo,routepermit_certificate_no,
            routepermit_expiry_date,    fitness_certificate_no,fitness_certificate_expiry_date,taxtoken_no,taxperiod_expiry_date;

    public vehicles(String id, String registration_No, String vehicle_Description, String vehicle_class, String CC, String color, String fuel, String seat, String engine_No, String chassis_No, String hire, String date, String wheel_Base, String issuing_Authority, String weight_Unladen, String weight_laden, String owner_name, String owner_address, String mfg_Year, String NID_No, String photo, String routepermit_certificate_no, String routepermit_expiry_date, String fitness_certificate_no, String fitness_certificate_expiry_date, String taxtoken_no, String taxperiod_expiry_date) {
        this.id = id;
        Registration_No = registration_No;
        Vehicle_Description = vehicle_Description;
        Vehicle_class = vehicle_class;
        this.CC = CC;
        Color = color;
        Fuel = fuel;
        Seat = seat;
        Engine_No = engine_No;
        Chassis_No = chassis_No;
        Hire = hire;
        Date = date;
        Wheel_Base = wheel_Base;
        Issuing_Authority = issuing_Authority;
        Weight_Unladen = weight_Unladen;
        Weight_laden = weight_laden;
        Owner_name = owner_name;
        Owner_address = owner_address;
        Mfg_Year = mfg_Year;
        this.NID_No = NID_No;
        this.photo = photo;
        this.routepermit_certificate_no = routepermit_certificate_no;
        this.routepermit_expiry_date = routepermit_expiry_date;
        this.fitness_certificate_no = fitness_certificate_no;
        this.fitness_certificate_expiry_date = fitness_certificate_expiry_date;
        this.taxtoken_no = taxtoken_no;
        this.taxperiod_expiry_date = taxperiod_expiry_date;
    }

    public vehicles(String id, String registration_No, String vehicle_Description, String vehicle_class, String CC, String color, String fuel, String seat, String engine_No, String chassis_No, String hire, String date, String wheel_Base, String issuing_Authority, String weight_Unladen, String weight_laden, String owner_name, String owner_address, String mfg_Year, String NID_No, String photo) {
        this.id = id;
        Registration_No = registration_No;
        Vehicle_Description = vehicle_Description;
        Vehicle_class = vehicle_class;
        this.CC = CC;
        Color = color;
        Fuel = fuel;
        Seat = seat;
        Engine_No = engine_No;
        Chassis_No = chassis_No;
        Hire = hire;
        Date = date;
        Wheel_Base = wheel_Base;
        Issuing_Authority = issuing_Authority;
        Weight_Unladen = weight_Unladen;
        Weight_laden = weight_laden;
        Owner_name = owner_name;
        Owner_address = owner_address;
        Mfg_Year = mfg_Year;
        this.NID_No = NID_No;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistration_No() {
        return Registration_No;
    }

    public void setRegistration_No(String registration_No) {
        Registration_No = registration_No;
    }

    public String getVehicle_Description() {
        return Vehicle_Description;
    }

    public void setVehicle_Description(String vehicle_Description) {
        Vehicle_Description = vehicle_Description;
    }

    public String getVehicle_class() {
        return Vehicle_class;
    }

    public void setVehicle_class(String vehicle_class) {
        Vehicle_class = vehicle_class;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getFuel() {
        return Fuel;
    }

    public void setFuel(String fuel) {
        Fuel = fuel;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public String getEngine_No() {
        return Engine_No;
    }

    public void setEngine_No(String engine_No) {
        Engine_No = engine_No;
    }

    public String getChassis_No() {
        return Chassis_No;
    }

    public void setChassis_No(String chassis_No) {
        Chassis_No = chassis_No;
    }

    public String getHire() {
        return Hire;
    }

    public void setHire(String hire) {
        Hire = hire;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWheel_Base() {
        return Wheel_Base;
    }

    public void setWheel_Base(String wheel_Base) {
        Wheel_Base = wheel_Base;
    }

    public String getIssuing_Authority() {
        return Issuing_Authority;
    }

    public void setIssuing_Authority(String issuing_Authority) {
        Issuing_Authority = issuing_Authority;
    }

    public String getWeight_Unladen() {
        return Weight_Unladen;
    }

    public void setWeight_Unladen(String weight_Unladen) {
        Weight_Unladen = weight_Unladen;
    }

    public String getWeight_laden() {
        return Weight_laden;
    }

    public void setWeight_laden(String weight_laden) {
        Weight_laden = weight_laden;
    }

    public String getOwner_name() {
        return Owner_name;
    }

    public void setOwner_name(String owner_name) {
        Owner_name = owner_name;
    }

    public String getOwner_address() {
        return Owner_address;
    }

    public void setOwner_address(String owner_address) {
        Owner_address = owner_address;
    }

    public String getMfg_Year() {
        return Mfg_Year;
    }

    public void setMfg_Year(String mfg_Year) {
        Mfg_Year = mfg_Year;
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

    public String getRoutepermit_certificate_no() {
        return routepermit_certificate_no;
    }

    public void setRoutepermit_certificate_no(String routepermit_certificate_no) {
        this.routepermit_certificate_no = routepermit_certificate_no;
    }

    public String getRoutepermit_expiry_date() {
        return routepermit_expiry_date;
    }

    public void setRoutepermit_expiry_date(String routepermit_expiry_date) {
        this.routepermit_expiry_date = routepermit_expiry_date;
    }

    public String getFitness_certificate_no() {
        return fitness_certificate_no;
    }

    public void setFitness_certificate_no(String fitness_certificate_no) {
        this.fitness_certificate_no = fitness_certificate_no;
    }

    public String getFitness_certificate_expiry_date() {
        return fitness_certificate_expiry_date;
    }

    public void setFitness_certificate_expiry_date(String fitness_certificate_expiry_date) {
        this.fitness_certificate_expiry_date = fitness_certificate_expiry_date;
    }

    public String getTaxtoken_no() {
        return taxtoken_no;
    }

    public void setTaxtoken_no(String taxtoken_no) {
        this.taxtoken_no = taxtoken_no;
    }

    public String getTaxperiod_expiry_date() {
        return taxperiod_expiry_date;
    }

    public void setTaxperiod_expiry_date(String taxperiod_expiry_date) {
        this.taxperiod_expiry_date = taxperiod_expiry_date;
    }
}

