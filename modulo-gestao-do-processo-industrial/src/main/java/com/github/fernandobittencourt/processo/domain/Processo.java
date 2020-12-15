package com.github.fernandobittencourt.processo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long area;
    private String dados;
    private Long consultoria;
    @OneToMany
    private List<Relatorio> relatorios;

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

    public List<Relatorio> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
    }
}
