package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VisitHistory {

    @Id
    private int pk_visit_id;

    private int fk_place_id;
    private String visit_date;
    private int visitors;
    private int spent_money;

}
