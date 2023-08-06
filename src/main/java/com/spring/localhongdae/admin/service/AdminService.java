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
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class AdminService implements InterAdminService {
    private final CityRepository cityRepository;
    private final DistrictsRepository districtsRepository;
    private final PlaceRepository placeRepository;
    private final VisitHistoryRepository visitHistoryRepository;
    private final FileManger fileManger;

    @Autowired
    public AdminService(CityRepository cityRepository, DistrictsRepository districtsRepository,
                        PlaceRepository placeRepository, VisitHistoryRepository visitHistoryRepository,
                        FileManger fileManger) {
        this.cityRepository = cityRepository;
        this.districtsRepository = districtsRepository;
        this.placeRepository = placeRepository;
        this.visitHistoryRepository = visitHistoryRepository;
        this.fileManger = fileManger;
    }

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
    public int registerRestaurant(MultipartHttpServletRequest mrequest) {
        int result = 0;
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

        try {
            String imageId = fileManger.imageUpload(file.getBytes(), file.getOriginalFilename(), path);
            Place place = placeRepository.save(new Place(districtId, placeName, latitude, longitude, imageId));
            VisitHistory visitHistory = visitHistoryRepository.save(new VisitHistory(place.getPlace_id(), visitDay, visitors, spentMoney));
            result = visitHistory.getPk_visit_id();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int registerVisitHistory(VisitHistory visitHistory) {
        int result = 1;
        try {
            visitHistoryRepository.save(visitHistory);
        } catch (IllegalArgumentException e) {
            result = 0;
        }
        return result;
    }

    @Override
    public List<Place> findPlaceList(HttpServletRequest request) {
        int districtId = Integer.parseInt(request.getParameter("districtId"));
        String placeName = request.getParameter("placeName");
        List<Place> placeList = placeRepository.findByDistrictIdAndPlaceName(districtId, placeName);
        return placeList;
    }

    // 음식점 이름과 지역 id를 활용해 이미 등록되어 있는지 조회
    @Override
    public Boolean isVisitPlace(HttpServletRequest request) {
        int districtId = Integer.parseInt(request.getParameter("selectedDistrict"));
        String placeName = request.getParameter("restaurantName");
        List<Place> placeList = placeRepository.findByDistrictIdAndPlaceName(districtId, placeName);

        if(placeList.isEmpty()) return false;
        else return true;
    }
}
