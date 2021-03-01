package com.github.fernandobittencourt.consultoria.domain.vo;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;

public class RelatorioVo {
    private Long processo;
    private String link;

    public RelatorioVo() {

    }

    public RelatorioVo(Arquivo arquivo) {
        this.processo = arquivo.getProcesso().getOrigemId();
        this.link = arquivo.getLink();
    }

    public Long getProcesso() {
        return processo;
    }

    public void setProcesso(Long processo) {
        this.processo = processo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
