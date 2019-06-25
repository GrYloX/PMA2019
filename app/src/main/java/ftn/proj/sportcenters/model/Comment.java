package ftn.proj.sportcenters.model;

import java.util.Date;

public class Comment {

    private int id;
    private int userId;
    private int sportCenterId;
    private Date commentDate;
    private String text;

    public Comment(){

    }

    public Comment(int id, int userId, int sportCenterId, Date commentDate, String text) {
        this.id = id;
        this.userId = userId;
        this.sportCenterId = sportCenterId;
        this.commentDate = commentDate;
        this.text = text;
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
