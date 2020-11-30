package com.github.fernandobittencourt.processo.domain.vo;

public class ProcessoDadosInclusaoVo {
    private Long area;
    private String dados;
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

    public Long getConsultoria() {
        return consultoria;
    }

    public void setConsultoria(Long consultoria) {
        this.consultoria = consultoria;
    }
}
