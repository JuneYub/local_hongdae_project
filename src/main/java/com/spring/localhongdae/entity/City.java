package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "cities")
public class City {
    @Id
    private int city_id;

    private String city_name;

}
