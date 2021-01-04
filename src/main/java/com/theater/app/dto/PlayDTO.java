package com.theater.app.dto;

import java.util.Date;

public class PlayDTO {

    private Long idPlay;
    private String title;
    private String synopsis;
    private Date date;

    public PlayDTO() {}

    public PlayDTO(Long idPlay, String title, String synopsis, Date date) {
        this.idPlay = idPlay;
        this.title = title;
        this.synopsis = synopsis;
        this.date = date;
    }

    public PlayDTO(String title, String synopsis, Date date) {
        this.title = title;
        this.synopsis = synopsis;
        this.date = date;
    }

    public Long getIdPlay() {
        return idPlay;
    }

    public void setIdPlay(Long idPlay) {
        this.idPlay = idPlay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PlayDTO{" +
                "idPlay=" + idPlay +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", date=" + date +
                '}';
    }
}
