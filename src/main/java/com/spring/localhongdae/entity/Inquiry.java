package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private int fk_user_no;
    private String fk_name;
    private String subject;
    private String content;
    private String pw;
    private String regDate;
    private int readCount;
    private int is_private;
    private int groupno;
    private int fk_seq;
    private int depthno;
}
