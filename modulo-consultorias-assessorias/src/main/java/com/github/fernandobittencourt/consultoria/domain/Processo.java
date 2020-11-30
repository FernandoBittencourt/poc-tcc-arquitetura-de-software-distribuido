package com.github.fernandobittencourt.consultoria.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Processo {

    @Id
    private Long id;
    private String dados;
    private Long origemId;
    private Consultoria consultoria;

    public Processo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    public Consultoria getConsultoria() {
        return consultoria;
    }

    public void setConsultoria(Consultoria consultoria) {
        this.consultoria = consultoria;
    }
}