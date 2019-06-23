package ftn.proj.sportcenters.model;

public class Invitation {

    public Invitation() {

    }

    public Invitation(int id, Reservation reservation, User user, boolean accepted) {
        this.id = id;
        this.reservation = reservation;
        this.user = user;
        this.accepted = accepted;
    }

    private int id;
    private Reservation reservation;
    private User user;
    private boolean accepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
