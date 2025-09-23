package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateur {

    public Client() {}

    public Client(String fullName, String email, String password, Role role) {
        super(null, fullName, email, password, role);
    }


    @Override
    public void seConnecter() {
    }

}