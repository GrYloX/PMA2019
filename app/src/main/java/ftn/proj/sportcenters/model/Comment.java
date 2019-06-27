package ftn.proj.sportcenters.model;

import java.util.Date;

public class Comment {

    private long id;
    private long userId;
    private long sportCenterId;
    private Date commentDate;
    private String text;

    public Comment(){

    }

    public Comment(long id, long userId, long sportCenterId, Date commentDate, String text) {
        this.id = id;
        this.userId = userId;
        this.sportCenterId = sportCenterId;
        this.commentDate = commentDate;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
