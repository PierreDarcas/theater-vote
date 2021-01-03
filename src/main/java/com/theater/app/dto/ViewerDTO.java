package com.theater.app.dto;

public class ViewerDTO {

    private Long idViewer;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public ViewerDTO() {}

    public ViewerDTO(Long idViewer, String firstname, String lastname, String email, String username, String password) {
        this.idViewer = idViewer;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public ViewerDTO(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getIdViewer() {
        return idViewer;
    }

    public void setIdViewer(Long idViewer) {
        this.idViewer = idViewer;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ViewerDTO{" +
                "idViewer=" + idViewer +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
