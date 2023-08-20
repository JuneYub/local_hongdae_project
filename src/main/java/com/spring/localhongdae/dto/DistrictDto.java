package com.spring.localhongdae.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDto {

    private int district_id;

    private String district_name;

    private int fk_city_id;

    private Double latitude;

    private Double longitude;

}
