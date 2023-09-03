package com.spring.localhongdae.restaurant.service;

import com.spring.localhongdae.admin.model.CityRepository;
import com.spring.localhongdae.admin.model.DistrictsRepository;
import com.spring.localhongdae.admin.model.PlaceRepository;
import com.spring.localhongdae.dto.PlaceDto;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.dto.DistrictDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RestaurantService implements InterRestaurant{

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictsRepository districtsRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public List<City> getCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public List<District> getDistricts(int cityId) {
        List<District> districts = districtsRepository.findAllByCity_id(cityId);
        return districts;
    }

    @Override
    public Page<Place> getPageByDistrictId(HttpServletRequest request, Pageable pageable) {
        Page<Place> placePage = null;
        try {
            int page = pageable.getPageNumber();
            log.info(String.valueOf(page));
            // page 번호 예외처리 (음수인 경우)
            if(page <= 0) {
                return null;
            }

            if(request.getParameter("selectedDistrict") == null || "".equals(request.getParameter("selectedDistrict").trim())) {
                return placePage;
            }

            int districtId = Integer.parseInt(request.getParameter("selectedDistrict"));
            PageRequest pageRequest = PageRequest.of(page-1, pageable.getPageSize(), Sort.by("place_id").descending());
            placePage = placeRepository.findByDistrictId(pageRequest, districtId);

            return placePage;
        }
        catch (NumberFormatException e) {
            // 도시 id 지역 id가 숫자가 아닌 값이 들어올 경우 예외처리
            log.info("NumberFormatException 에러 발생 : " + e.toString());
            return null;
        }

    }

    @Override
    public DistrictDto getDistrictInfoByDistrictId(int districtId) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(districtsRepository.findByDistricId(districtId), DistrictDto.class);
    }

    @Override
    public PlaceDto getPlaceById(int placeId) {
        ModelMapper modelMapper = new ModelMapper();
        Place place = placeRepository.findByPlaceId(placeId);
        return modelMapper.map(place, PlaceDto.class);
    }
}
