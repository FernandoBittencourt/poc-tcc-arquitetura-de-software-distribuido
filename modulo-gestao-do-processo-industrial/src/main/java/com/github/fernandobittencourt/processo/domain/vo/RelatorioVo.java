package com.github.fernandobittencourt.processo.domain.vo;

public class RelatorioVo {
    private Long processo;
    private String link;

    public RelatorioVo() {

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
