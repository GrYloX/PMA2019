package ftn.proj.sportcenters.model;

public class Invitation {

    public Invitation() {

    }

    public Invitation(int id, int reservationId, int userId, boolean accepted) {
        this.id = id;
        this.reservationId = reservationId;
        this.userId = userId;
        this.accepted = accepted;
    }

    private int id;
    private int reservationId;
    private int userId;
    private boolean accepted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
