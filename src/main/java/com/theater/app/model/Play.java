package com.theater.app.model;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity(name = "Play")
@Getter
@Setter
public class Play extends BaseEntity{

    private String title;
    @Lob
    private String synopsis;
    private Date date;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private Set<Review> reviews;
}
