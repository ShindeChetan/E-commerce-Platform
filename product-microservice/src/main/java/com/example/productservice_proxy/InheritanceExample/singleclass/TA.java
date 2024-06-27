package com.example.productservice_proxy.InheritanceExample.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "sc_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private double rating;
}
