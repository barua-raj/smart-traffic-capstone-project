package com.thesis.trafficinfo.LoginAndSignUp;

public class History {
    String id,Applicant_name,Applicant_type,License_no,
            crime,imprisonment,fine,fine_type,Vehicle_type,
            Vehicle_number,Type_of_fine,NID_No,Police_id,
            feedback,Status,photo,case_date;

    public History(String id, String applicant_name, String applicant_type, String license_no, String crime, String imprisonment, String fine, String fine_type, String vehicle_type, String vehicle_number, String type_of_fine, String NID_No, String police_id, String feedback, String status, String photo, String case_date) {
        this.id = id;
        Applicant_name = applicant_name;
        Applicant_type = applicant_type;
        License_no = license_no;
        this.crime = crime;
        this.imprisonment = imprisonment;
        this.fine = fine;
        this.fine_type = fine_type;
        Vehicle_type = vehicle_type;
        Vehicle_number = vehicle_number;
        Type_of_fine = type_of_fine;
        this.NID_No = NID_No;
        Police_id = police_id;
        this.feedback = feedback;
        Status = status;
        this.photo = photo;
        this.case_date = case_date;
    }

    public History(String id, String applicant_name, String applicant_type, String license_no, String crime, String imprisonment, String fine, String fine_type, String vehicle_type, String vehicle_number, String type_of_fine, String NID_No, String police_id, String feedback, String status, String photo) {
        this.id = id;
        Applicant_name = applicant_name;
        Applicant_type = applicant_type;
        License_no = license_no;
        this.crime = crime;
        this.imprisonment = imprisonment;
        this.fine = fine;
        this.fine_type = fine_type;
        Vehicle_type = vehicle_type;
        Vehicle_number = vehicle_number;
        Type_of_fine = type_of_fine;
        this.NID_No = NID_No;
        Police_id = police_id;
        this.feedback = feedback;
        Status = status;
        this.photo = photo;
    }

    public History(String id, String applicant_name, String applicant_type, String license_no, String crime, String imprisonment, String fine, String fine_type, String vehicle_type, String vehicle_number, String type_of_fine, String NID_No, String police_id, String feedback, String status) {
        this.id = id;
        Applicant_name = applicant_name;
        Applicant_type = applicant_type;
        License_no = license_no;
        this.crime = crime;
        this.imprisonment = imprisonment;
        this.fine = fine;
        this.fine_type = fine_type;
        Vehicle_type = vehicle_type;
        Vehicle_number = vehicle_number;
        Type_of_fine = type_of_fine;
        this.NID_No = NID_No;
        Police_id = police_id;
        this.feedback = feedback;
        Status = status;
    }

    public String getCase_date() {
        return case_date;
    }

    public void setCase_date(String case_date) {
        this.case_date = case_date;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicant_name() {
        return Applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        Applicant_name = applicant_name;
    }

    public String getApplicant_type() {
        return Applicant_type;
    }

    public void setApplicant_type(String applicant_type) {
        Applicant_type = applicant_type;
    }

    public String getLicense_no() {
        return License_no;
    }

    public void setLicense_no(String license_no) {
        License_no = license_no;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getImprisonment() {
        return imprisonment;
    }

    public void setImprisonment(String imprisonment) {
        this.imprisonment = imprisonment;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getFine_type() {
        return fine_type;
    }

    public void setFine_type(String fine_type) {
        this.fine_type = fine_type;
    }

    public String getVehicle_type() {
        return Vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        Vehicle_type = vehicle_type;
    }

    public String getVehicle_number() {
        return Vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        Vehicle_number = vehicle_number;
    }

    public String getType_of_fine() {
        return Type_of_fine;
    }

    public void setType_of_fine(String type_of_fine) {
        Type_of_fine = type_of_fine;
    }

    public String getNID_No() {
        return NID_No;
    }

    public void setNID_No(String NID_No) {
        this.NID_No = NID_No;
    }

    public String getPolice_id() {
        return Police_id;
    }

    public void setPolice_id(String police_id) {
        Police_id = police_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
