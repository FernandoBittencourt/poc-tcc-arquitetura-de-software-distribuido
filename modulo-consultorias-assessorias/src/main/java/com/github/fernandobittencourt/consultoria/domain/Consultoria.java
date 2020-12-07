package com.github.fernandobittencourt.consultoria.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Consultoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToMany
    private List<Norma> normas;
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

    public List<Norma> getNormas() {
        return normas;
    }

    public void setNormas(List<Norma> normas) {
        this.normas = normas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}