package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "social_login")
public class SocialLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int social_login_id;

    private int fk_user_no;
    private int social_code;
    private String external_id;
    private String access_token;
    private String update_date;

}
