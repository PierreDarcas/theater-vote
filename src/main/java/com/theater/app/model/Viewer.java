package com.theater.app.model;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Viewer")
@Getter
@Setter
public class Viewer extends BaseEntity{

    private String firstname;
    private String lastname;
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private Set<Review> reviews;

}
