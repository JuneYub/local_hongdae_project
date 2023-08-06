package com.spring.localhongdae.admin.service;
import com.spring.localhongdae.entity.City;
import com.spring.localhongdae.entity.District;
import com.spring.localhongdae.entity.VisitHistory;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Transactional
@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    private MockMvc mockMvc;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Test
    @DisplayName("Cities 조회 테스트")
    void getCitiesTest() throws Exception {
        List<City> list = adminService.getCities();
        for (City city: list) {
            logger.info(city.getCity_name());
        }
    }

    @Test
    @DisplayName("Districts 조회 테스트")
    void getDistrictsTest() throws Exception {
        int cityId = 11; // 서울특별시
        List<District> list = adminService.getDistrictsById(11);
        assertThat(list.size()).isEqualTo(21);
    }

    @Test
    @DisplayName("방문 기록 추가 기능 테스트")
    void registerVisitHistoryTest() {
        // given
        int placeid = 6; // 서울시 종로구 호랑이도삭면
        VisitHistory visitHistory =
                VisitHistory.builder()
                .fk_place_id(placeid)
                .visit_date("2023-05-05") // yyyy-MM-dd
                .visitors(3)
                .spent_money(30000)
                .build();
        // when
        int result = adminService.registerVisitHistory(visitHistory);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("방문 기록 추가 기능 테스트")
    void X_registerVisitHistoryTest() {
        // given
        int placeid = 1; // 없는 placeid
        VisitHistory visitHistory =
                VisitHistory.builder()
                        .fk_place_id(placeid)
                        .visit_date("2023-05-05") // yyyy-MM-dd
                        .visitors(3)
                        .spent_money(30000)
                        .build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            // when
            int result = adminService.registerVisitHistory(visitHistory);
            // then 무결성 제약조건 예외 발생
        });
    }

    @Test
    @DisplayName("방문 여부를 확인하는 테스트")
    void isVisitPlaceTest () {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        Mockito.when(mockRequest.getParameter("selectedDistrict")).thenReturn("110");
        Mockito.when(mockRequest.getParameter("restaurantName")).thenReturn("호랑이도삭면");
        assertTrue(adminService.isVisitPlace(mockRequest));
    }
}