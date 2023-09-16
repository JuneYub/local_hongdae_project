package com.spring.localhongdae.common.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

@Slf4j
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if(values == null) return null;

        int count = values.length;
        String[] encodedValues = new String[count];
        for(int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if(value == null) return null;
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if(value == null) return null;
        return cleanXSS(value);
    }

    private String cleanXSS(String orgValue) {
        String value = orgValue;
        if(value != null) {
            value = HtmlUtils.htmlEscape(value);
        }
        return value;
    }

}
