package com.spring.localhongdae.dto;

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

}
