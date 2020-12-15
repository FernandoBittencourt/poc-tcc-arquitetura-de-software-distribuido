package com.github.fernandobittencourt.processo.domain.vo;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.Relatorio;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessoVo {
    private Long id;
    private Long area;
    private String dados;
    private Long consultoria;
    private List<String> relatorios;

    public ProcessoVo(Processo processo) {
        this.id = processo.getId();
        this.area = processo.getArea();
        this.dados = processo.getDados();
        this.consultoria = processo.getConsultoria();
        this.relatorios= new ArrayList<>();
    }

    public ProcessoVo(Processo processo, List<Relatorio> relatorios) {
        this.id = processo.getId();
        this.area = processo.getArea();
        this.dados = processo.getDados();
        this.consultoria = processo.getConsultoria();
        this.relatorios=relatorios.stream()
                .map(Relatorio::getLink)
                .collect(Collectors.toList());
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
