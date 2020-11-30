package com.github.fernandobittencourt.norma.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Norma {

    @Id
    private Long id;

    private String codigo;

    private String versao;

    private String conteudo;


    public Norma() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
