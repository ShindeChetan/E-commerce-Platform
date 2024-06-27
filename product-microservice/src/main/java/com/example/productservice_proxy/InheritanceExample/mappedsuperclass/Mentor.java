package com.example.productservice_proxy.InheritanceExample.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "mps_mentor")
public class Mentor extends User {
    private int gradYear;
}
