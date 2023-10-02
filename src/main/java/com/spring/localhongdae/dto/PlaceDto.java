package com.spring.localhongdae.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceDto {
    private int place_id;
    private int fk_district_id;
    private String place_name;
    private Double latitude;
    private Double longitude;
    private String image_id;
    private int visit_count;

    public PlaceDto(int place_id, int fk_district_id, String place_name, Double latitude, Double longitude, String image_id, int visit_count) {
        this.place_id = place_id;
        this.fk_district_id = fk_district_id;
        this.place_name = place_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image_id = image_id;
        this.visit_count = visit_count;
    }
}
