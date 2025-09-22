package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name = "coaches")
@DiscriminatorValue("COACH")
public class Coach extends Utilisateur {

    @Column(nullable = false)
    private String specialite;


    public Coach() {}

    @Override
    public void seConnecter() {
    }

    public Coach(String fullName, String email, String password, Role role, String specialite) {
        super(null, fullName, email, password, role);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    @Override
    @JsonIgnore
    public String getPassword() {
        return super.getPassword();
    }


    @Override
    @JsonProperty
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
