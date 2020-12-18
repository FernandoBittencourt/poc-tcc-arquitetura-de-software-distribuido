package com.github.fernandobittencourt.processo.domain.vo;

import java.util.List;

public class ConsultoriaVo {
    private Long id;
    private String nome;
    private List<ConsultoriaNormaVo> normas;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ConsultoriaNormaVo> getNormas() {
        return normas;
    }

    public void setNormas(List<ConsultoriaNormaVo> normas) {
        this.normas = normas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
