package com.spring.localhongdae.admin.model;



import com.spring.localhongdae.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findAllBy();
}
