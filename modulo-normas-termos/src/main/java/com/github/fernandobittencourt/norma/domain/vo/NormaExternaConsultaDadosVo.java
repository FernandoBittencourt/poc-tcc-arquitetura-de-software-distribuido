package com.github.fernandobittencourt.norma.domain.vo;

public class NormaExternaConsultaDadosVo {
    private String codigo;

    public NormaExternaConsultaDadosVo(){

    }
    public NormaExternaConsultaDadosVo(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
