package com.spring.localhongdae.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "login_log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int login_log_id;

    private  int fk_user_no;
    private int status_code;
    private String ip;

    public int getLogin_log_id() {
        return login_log_id;
    }

    public void setLogin_log_id(int login_log_id) {
        this.login_log_id = login_log_id;
    }

    public int getFk_user_no() {
        return fk_user_no;
    }

    public void setFk_user_no(int fk_user_no) {
        this.fk_user_no = fk_user_no;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
