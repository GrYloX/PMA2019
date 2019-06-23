package ftn.proj.sportcenters.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

    private int id;
    private SportCenter sportCenter;
    private String sport;
    private double price;
    private Date date;
    private String period;

    public Reservation() {
    }

    public Reservation(int id, SportCenter sportCenter, String sport, double price, Date date, String period) {
        this.id = id;
        this.sportCenter = sportCenter;
        this.sport = sport;
        this.price = price;
        this.date = date;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
