package com.klef.practice.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "report_table")
public class Report {
    @Id
    @Column(name = "report_id")
    private int id;

    @Column(name = "citizen_name", nullable = false, length = 50)
    private String citizenName;

    @Column(name = "citizen_contact", nullable = false, length = 20)
    private String contact;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "weather_condition", nullable = false, length = 50)
    private String condition; 

    @Column(name = "temperature", nullable = true, length = 10)
    private String temperature;

    @Column(name = "report_date", nullable = false, length = 30)
    private String date; 

    @Column(name = "additional_notes", length = 255)
    private String notes;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCitizenName() {
        return citizenName;
    }
    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Report [id=" + id + ", citizenName=" + citizenName + ", contact=" + contact
                + ", location=" + location + ", condition=" + condition + ", temperature=" + temperature
                + ", date=" + date + ", notes=" + notes + "]";
    }
}

