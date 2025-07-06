package com.thesis.trafficinfo.Models;

public class laws {
    String id, section, crime_type, imprisonment, fine,first_fine,next_fine_s;

    public laws(String id, String section, String crime_type, String imprisonment, String fine, String first_fine, String next_fine_s) {
        this.id = id;
        this.section = section;
        this.crime_type = crime_type;
        this.imprisonment = imprisonment;
        this.fine = fine;
        this.first_fine = first_fine;
        this.next_fine_s = next_fine_s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCrime_type() {
        return crime_type;
    }

    public void setCrime_type(String crime_type) {
        this.crime_type = crime_type;
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

    public String getFirst_fine() {
        return first_fine;
    }

    public void setFirst_fine(String first_fine) {
        this.first_fine = first_fine;
    }

    public String getNext_fine_s() {
        return next_fine_s;
    }

    public void setNext_fine_s(String next_fine_s) {
        this.next_fine_s = next_fine_s;
    }
}
