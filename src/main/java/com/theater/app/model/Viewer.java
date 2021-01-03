package com.theater.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Viewer")
@Getter
@Setter
public class Viewer extends BaseEntity{

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

}
