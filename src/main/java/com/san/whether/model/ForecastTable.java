package com.san.whether.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.GregorianCalendar;

@Document(collection = "forecast")
public class ForecastTable {

    public ForecastTable() {
        this.createdDate = new java.sql.Date(GregorianCalendar.getInstance().getTime().getTime());
    }

    @Id
    private String id;

    private Date createdDate;

    String location;

    Forecast forecast;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }


}
