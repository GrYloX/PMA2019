package ftn.proj.sportcenters.model;

import java.io.Serializable;

public class Reservation implements Serializable {

    private int id;
    private int userId;
    private int sportCenterId;
    private int sportId;
    private double price;
    private String date;
    private String period;

    public Reservation() {
    }

    public Reservation(int id, int userId, int sportCenterId, int sportId, double price, String date, String period) {
        this.id = id;
        this.userId = userId;
        this.sportCenterId = sportCenterId;
        this.sportId = sportId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSportCenterId() {
        return sportCenterId;
    }

    public void setSportCenterId(int sportCenterId) {
        this.sportCenterId = sportCenterId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
