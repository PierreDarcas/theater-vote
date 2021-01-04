package com.theater.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

@Entity(name = "Play")
@Getter
@Setter
public class Play extends BaseEntity{

    private String title;
    @Lob
    private String synopsis;
    private Date date;

}
