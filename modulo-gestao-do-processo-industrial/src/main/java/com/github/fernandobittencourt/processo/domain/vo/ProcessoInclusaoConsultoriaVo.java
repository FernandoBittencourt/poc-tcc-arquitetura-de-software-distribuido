package com.github.fernandobittencourt.processo.domain.vo;

import com.github.fernandobittencourt.processo.domain.Processo;

public class ProcessoInclusaoConsultoriaVo {
    private String dados;
    private Long origemId;

    public ProcessoInclusaoConsultoriaVo() {
    }

    public ProcessoInclusaoConsultoriaVo(Processo processo) {
        this.dados = processo.getDados();
        this.origemId = processo.getId();
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
}
