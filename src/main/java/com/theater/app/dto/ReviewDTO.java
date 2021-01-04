package com.theater.app.dto;


public class ReviewDTO {

    private Long idReview;
    private String title;
    private int note;
    private String comment;
    private Long viewerId;
    private Long playId;

    public ReviewDTO(){}

    public ReviewDTO(Long idReview, String title, int note, String comment, Long viewerId, Long playId) {
        this.idReview = idReview;
        this.title = title;
        this.note = note;
        this.comment = comment;
        this.viewerId = viewerId;
        this.playId = playId;
    }

    public ReviewDTO(String title, int note, String comment, Long viewerId, Long playId) {
        this.title = title;
        this.note = note;
        this.comment = comment;
        this.viewerId = viewerId;
        this.playId = playId;
    }

    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getViewerId() {
        return viewerId;
    }

    public void setViewerId(Long viewerId) {
        this.viewerId = viewerId;
    }

    public Long getPlayId() {
        return playId;
    }

    public void setPlayId(Long playId) {
        this.playId = playId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "idReview=" + idReview +
                ", title='" + title + '\'' +
                ", note=" + note +
                ", comment='" + comment + '\'' +
                ", viewerId=" + viewerId +
                ", playId=" + playId +
                '}';
    }
}
