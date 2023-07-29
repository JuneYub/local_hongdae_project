package com.spring.localhongdae.admin.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private static final Logger logger = LoggerFactory.getLogger(AdminControllerTest.class);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("음식점 처음 등록 테스트")
    void registerPlaceFirstTest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("fileName", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "010101".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/registerRestaurant")
                        .file(file)
                        .param("selectedCity", "11")
                        .param("selectedDistrict", "110")
                        .param("restaurantName", "테스트 음식점명")
                        .param("visitDay", "20230707")
                        .param("visitorsNum", "3")
                        .param("spendMoney", "30,000")
                        .param("latitude", "23.23")
                        .param("longitude", "17.17"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    if(response.equals("0")) {
                        throw new AssertionError("테스트 실패 - 응답 값이 0 이면 데이터 입력 실패");
                    }
                });
    }
}