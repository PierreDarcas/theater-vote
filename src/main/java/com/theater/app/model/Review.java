package com.theater.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Review")
@Getter
@Setter
public class Review extends BaseEntity {

    private String title;
    private int note;
    @Lob
    private String comment;

    @ManyToOne
    @JoinColumn(name = "viewer_id")
    private Viewer viewer;

    @ManyToOne
    @JoinColumn(name = "play_id")
    private Play play;

}
