package com.github.fernandobittencourt.consultoria.domain;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

public class Arquivo {
    @Id
    private Long id;
    @OneToOne
    private Processo processo;
    private String link;
    private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
