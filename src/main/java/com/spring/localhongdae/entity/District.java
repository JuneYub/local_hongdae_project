package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "districts")
public class District {
    @Id
    private int district_id;

    private String district_name;

    private int city_id;
}
