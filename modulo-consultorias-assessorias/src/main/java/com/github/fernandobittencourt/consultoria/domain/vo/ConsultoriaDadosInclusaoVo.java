package com.github.fernandobittencourt.consultoria.domain.vo;

import java.util.List;

public class ConsultoriaDadosInclusaoVo {
    private String nome;
    private List<String> normas;
    private Boolean status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getNormas() {
        return normas;
    }

    public void setNormas(List<String> normas) {
        this.normas = normas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
