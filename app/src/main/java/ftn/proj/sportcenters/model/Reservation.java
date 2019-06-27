package ftn.proj.sportcenters.model;

import java.io.Serializable;

public class Reservation implements Serializable {

    private long id;
    private long userId;
    private long sportCenterId;
    private long sportId;
    private double price;
    private String date;
    private String period;

    public Reservation() {
    }

    public Reservation(long id, long userId, long sportCenterId, long sportId, double price, String date, String period) {
        this.id = id;
        this.userId = userId;
        this.sportCenterId = sportCenterId;
        this.sportId = sportId;
        this.price = price;
        this.date = date;
        this.period = period;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSportCenterId() {
        return sportCenterId;
    }

    public void setSportCenterId(long sportCenterId) {
        this.sportCenterId = sportCenterId;
    }

    public long getSportId() {
        return sportId;
    }

    public void setSportId(long sportId) {
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
