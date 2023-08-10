package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "districts")
public class District {
    @Id
    private int district_id;

    private String district_name;

    private int fk_city_id;
}
