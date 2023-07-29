package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VisitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public VisitHistory() {}

    public int getPk_visit_id() {
        return pk_visit_id;
    }

    public void setPk_visit_id(int pk_visit_id) {
        this.pk_visit_id = pk_visit_id;
    }

    public int getFk_place_id() {
        return fk_place_id;
    }

    public void setFk_place_id(int fk_place_id) {
        this.fk_place_id = fk_place_id;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public int getVisitors() {
        return visitors;
    }

    public void setVisitors(int visitors) {
        this.visitors = visitors;
    }

    public int getSpent_money() {
        return spent_money;
    }

    public void setSpent_money(int spent_money) {
        this.spent_money = spent_money;
    }
}
