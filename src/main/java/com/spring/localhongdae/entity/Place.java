package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int place_id;

    private int fk_district_id;
    private String place_name;
    private Double latitude;
    private Double longitude;
    private String image_id;

    public Place(int fk_district_id, String place_name, Double latitude, Double longitude, String image_id) {
        this.fk_district_id = fk_district_id;
        this.place_name = place_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image_id = image_id;
    }

    public Place() {}

}
