package com.github.fernandobittencourt.processo.domain.vo;

public class ProcessoDadosInclusaoVo {
    private Long area;
    private String dados;
    private String norma;
    private Long consultoria;

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    public Long getConsultoria() {
        return consultoria;
    }

    public void setConsultoria(Long consultoria) {
        this.consultoria = consultoria;
    }
}
