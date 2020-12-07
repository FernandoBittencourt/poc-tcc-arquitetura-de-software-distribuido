package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.domain.vo.ProcessoDadosInclusaoVo;
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

    @Autowired
    private ConsultoriaRepository consultoriaRepository;

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

    public Processo incluirProcesso(Long consultoriaId, ProcessoDadosInclusaoVo dados) {
        Consultoria consultoria = consultoriaRepository.findById(consultoriaId).orElseThrow(RuntimeException::new);
        Processo processo = new Processo();
        processo.setDados(dados.getDados());
        processo.setOrigemId(dados.getOrigemId());
        processo.setConsultoria(consultoria);
        return repository.save(processo);
    }

    public Processo atualizarProcesso(Long consultoriaId, Long processoId, ProcessoDadosInclusaoVo dados) {
        Consultoria consultoria = consultoriaRepository.findById(consultoriaId).orElseThrow(RuntimeException::new);
        Processo processo = repository.findById(processoId).orElseThrow(RuntimeException::new);
        processo.setDados(dados.getDados());
        processo.setOrigemId(dados.getOrigemId());
        processo.setConsultoria(consultoria);
        return repository.save(processo);
    }
}
