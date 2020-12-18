package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.vo.ConsultoriaVo;

public interface IConsultoriaService {
    ConsultoriaVo obterConsultoria(Long id);
    void propagarProcesso(Processo processo);
    void atualizarProcesso(Processo processo);
}
