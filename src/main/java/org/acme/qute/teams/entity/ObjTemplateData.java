package org.acme.qute.teams.entity;

import io.quarkus.qute.TemplateData;

import java.math.BigDecimal;

@TemplateData
public class ObjTemplateData {

    public final String body = "hello im content";
    public final BigDecimal value = new BigDecimal("-1.12345");

    public ObjTemplateData() {
    }

    public String getBody() {
        return body;
    }

    public BigDecimal getValue() {
        return value;
    }
}
