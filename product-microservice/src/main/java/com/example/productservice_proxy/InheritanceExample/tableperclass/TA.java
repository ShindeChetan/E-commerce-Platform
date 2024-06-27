package com.example.productservice_proxy.InheritanceExample.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User{
    private double rating;
}
