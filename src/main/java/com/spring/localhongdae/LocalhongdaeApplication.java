package com.spring.localhongdae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

@SpringBootApplication()
@EnableAspectJAutoProxy
public class LocalhongdaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalhongdaeApplication.class, args);
    }

}

