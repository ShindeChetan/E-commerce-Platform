package com.example.productservice_proxy.InheritanceExample.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User{
    private int gradYear;
}
