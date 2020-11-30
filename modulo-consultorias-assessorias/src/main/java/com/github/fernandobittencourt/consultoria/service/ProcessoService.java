package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.repository.ConsultoriaRepository;
import com.github.fernandobittencourt.consultoria.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository;

    public Processo obterProcessosPorConsultoria(Long consultoria) {
        //TODO: return repository.findByConsultoria(consultoria);
    }

    public Processo obterProcesso(Long consultoria, Long processo) {
        //TODO: return repository.findByConsultoriaAndId(consultoria, processo);
    }
}
