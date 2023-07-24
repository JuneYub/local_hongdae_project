package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "districts")
public class District {
    @Id
    private int district_id;

    private String district_name;

    private int fk_city_id;

    public int getDistrict_id() {
        return district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public int getCity_id() {
        return fk_city_id;
    }
}
