package com.github.fernandobittencourt.processo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Processo {

    @Id
    private Long id;
    private Long area;
    private String dados;
    private Long consultoria;
    private List<String> relatorios;

    public Processo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<String> relatorios) {
        this.relatorios = relatorios;
    }
}
