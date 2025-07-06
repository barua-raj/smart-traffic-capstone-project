package com.thesis.trafficinfo.Models;

public class Fee {
    String id,	Registration_no,Ownership_type,CC,Tax_token_expire,
            Fitness_expire,Road_permit_expire,Rule,Main_Fees,Extra_price,
            Inspection_fee,Label_fees,Application_fee,Delay_fines,Other_fees,
            VAT,Total_fees;

    public Fee(String id, String registration_no, String ownership_type, String CC, String tax_token_expire, String fitness_expire, String road_permit_expire, String rule, String main_Fees, String extra_price, String inspection_fee, String label_fees, String application_fee, String delay_fines, String other_fees, String VAT, String total_fees) {
        this.id = id;
        Registration_no = registration_no;
        Ownership_type = ownership_type;
        this.CC = CC;
        Tax_token_expire = tax_token_expire;
        Fitness_expire = fitness_expire;
        Road_permit_expire = road_permit_expire;
        Rule = rule;
        Main_Fees = main_Fees;
        Extra_price = extra_price;
        Inspection_fee = inspection_fee;
        Label_fees = label_fees;
        Application_fee = application_fee;
        Delay_fines = delay_fines;
        Other_fees = other_fees;
        this.VAT = VAT;
        Total_fees = total_fees;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistration_no() {
        return Registration_no;
    }

    public void setRegistration_no(String registration_no) {
        Registration_no = registration_no;
    }

    public String getOwnership_type() {
        return Ownership_type;
    }

    public void setOwnership_type(String ownership_type) {
        Ownership_type = ownership_type;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getTax_token_expire() {
        return Tax_token_expire;
    }

    public void setTax_token_expire(String tax_token_expire) {
        Tax_token_expire = tax_token_expire;
    }

    public String getFitness_expire() {
        return Fitness_expire;
    }

    public void setFitness_expire(String fitness_expire) {
        Fitness_expire = fitness_expire;
    }

    public String getRoad_permit_expire() {
        return Road_permit_expire;
    }

    public void setRoad_permit_expire(String road_permit_expire) {
        Road_permit_expire = road_permit_expire;
    }

    public String getRule() {
        return Rule;
    }

    public void setRule(String rule) {
        Rule = rule;
    }

    public String getMain_Fees() {
        return Main_Fees;
    }

    public void setMain_Fees(String main_Fees) {
        Main_Fees = main_Fees;
    }

    public String getExtra_price() {
        return Extra_price;
    }

    public void setExtra_price(String extra_price) {
        Extra_price = extra_price;
    }

    public String getInspection_fee() {
        return Inspection_fee;
    }

    public void setInspection_fee(String inspection_fee) {
        Inspection_fee = inspection_fee;
    }

    public String getLabel_fees() {
        return Label_fees;
    }

    public void setLabel_fees(String label_fees) {
        Label_fees = label_fees;
    }

    public String getApplication_fee() {
        return Application_fee;
    }

    public void setApplication_fee(String application_fee) {
        Application_fee = application_fee;
    }

    public String getDelay_fines() {
        return Delay_fines;
    }

    public void setDelay_fines(String delay_fines) {
        Delay_fines = delay_fines;
    }

    public String getOther_fees() {
        return Other_fees;
    }

    public void setOther_fees(String other_fees) {
        Other_fees = other_fees;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public String getTotal_fees() {
        return Total_fees;
    }

    public void setTotal_fees(String total_fees) {
        Total_fees = total_fees;
    }
}
