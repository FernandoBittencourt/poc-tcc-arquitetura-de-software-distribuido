package com.github.fernandobittencourt.consultoria.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Consultoria {

    @Id
    private Long id;
    private String nome;
    private List<String> normas;
    private Boolean status;


    public Consultoria() {
    }

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