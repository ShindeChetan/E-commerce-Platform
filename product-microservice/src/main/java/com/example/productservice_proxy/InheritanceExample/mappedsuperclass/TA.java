package com.example.productservice_proxy.InheritanceExample.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "mps_ta")
public class TA extends User {
    private double rating;
}
