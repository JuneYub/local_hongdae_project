package com.spring.localhongdae.place.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlaceVisitCntDTO {
    private int place_id;
    private int fk_district_id;
    private String place_name;
    private Double latitude;
    private Double longitude;
    private String image_id;
}
