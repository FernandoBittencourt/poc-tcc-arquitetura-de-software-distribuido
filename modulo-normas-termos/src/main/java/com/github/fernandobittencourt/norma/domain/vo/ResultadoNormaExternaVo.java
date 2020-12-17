package com.github.fernandobittencourt.norma.domain.vo;

public class ResultadoNormaExternaVo {
    private Integer statusCode;

    private String body;

    public ResultadoNormaExternaVo(){

    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
