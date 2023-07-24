package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "cities")
public class City {
    @Id
    private int city_id;

    private String city_name;

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }
}
