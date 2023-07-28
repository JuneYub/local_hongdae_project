package com.spring.localhongdae.admin.service;

import com.spring.localhongdae.admin.model.CityRepository;
import com.spring.localhongdae.admin.model.DistrictsRepository;
import com.spring.localhongdae.admin.model.PlaceRepository;
import com.spring.localhongdae.admin.model.VisitHistoryRepository;
import com.spring.localhongdae.common.FileManger;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.Place;
import com.spring.localhongdae.entity.VisitHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class AdminService implements InterAdminService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictsRepository districtsRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    VisitHistoryRepository visitHistoryRepository;

    @Autowired
    FileManger fileManger;

    @Override
    public List<City> getCities() {
        List<City> cities = cityRepository.findAllBy();
        return cities;
    }

    @Override
    public List<District> getDistrictsById(int cityId) {
        List<District> districts = districtsRepository.findAllByCity_id(cityId);
        return districts;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerRestaurant(MultipartHttpServletRequest mrequest) {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\restaurant";

        int cityId = Integer.parseInt((String) mrequest.getParameter("selectedCity"));
        int districtId = Integer.parseInt((String) mrequest.getParameter("selectedDistrict"));
        String placeName = (String) mrequest.getParameter("restaurantName");
        String visitDay = (String) mrequest.getParameter("visitDay");
        int visitors = Integer.parseInt((String) mrequest.getParameter("visitorsNum"));
        int spentMoney = Integer.parseInt(mrequest.getParameter("spendMoney").toString().replace(",",""));
        Double latitude = Double.parseDouble((String) mrequest.getParameter("latitude"));
        Double longitude = Double.parseDouble((String) mrequest.getParameter("longitude"));
        MultipartFile file = mrequest.getFile("fileName");

        log.info("fileName :" + file.getOriginalFilename());
        log.info("fileName :" + file.getName());

        try {
            String imageId = fileManger.imageUpload(file.getBytes(), file.getOriginalFilename(), path);
            Place place = placeRepository.save(new Place(districtId, placeName, latitude, longitude, imageId));
            visitHistoryRepository.save(new VisitHistory(place.getPlace_id(), visitDay, visitors, spentMoney));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
