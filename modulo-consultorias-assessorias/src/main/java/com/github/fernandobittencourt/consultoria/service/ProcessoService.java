package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.repository.ConsultoriaRepository;
import com.github.fernandobittencourt.consultoria.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository;

    public List<Processo> obterProcessosPorConsultoria(Long consultoriaId) {
        return repository.findByConsultoria(consultoriaId);
    }

    public Processo obterProcesso(Long consultoriaId, Long processoId) {
        Processo processo = repository.findById(processoId).orElseThrow(RuntimeException::new);
        if(processo.getConsultoria() == null || !processo.getConsultoria().getId().equals(consultoriaId)){
            throw new RuntimeException();
        }
        return processo;
    }
}
