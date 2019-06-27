package ftn.proj.sportcenters.model;

public class Invitation {

    public Invitation() {

    }

    public Invitation(long id, long reservationId, long userId, boolean accepted) {
        this.id = id;
        this.reservationId = reservationId;
        this.userId = userId;
        this.accepted = accepted;
    }

    private long id;
    private long reservationId;
    private long userId;
    private boolean accepted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
