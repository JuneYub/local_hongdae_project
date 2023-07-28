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

    public VisitHistory(int fk_place_id, String visit_date, int visitors, int spent_money) {
        this.fk_place_id = fk_place_id;
        this.visit_date = visit_date;
        this.visitors = visitors;
        this.spent_money = spent_money;
    }
}
