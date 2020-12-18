package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Norma;
import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.Relatorio;
import com.github.fernandobittencourt.processo.domain.vo.ConsultoriaNormaVo;
import com.github.fernandobittencourt.processo.domain.vo.ConsultoriaVo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoVo;
import com.github.fernandobittencourt.processo.repository.ProcessoRepository;
import com.github.fernandobittencourt.processo.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcessoService {

    @Autowired
    private INormaService normaService;

    @Autowired
    private IConsultoriaService consultoriaService;

    @Autowired
    private ProcessoRepository repository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<ProcessoVo> obterProcessos() {
        return repository.findAll().stream()
                .map(this::obterProcessoVo)
                .collect(Collectors.toList());
    }

    public ProcessoVo obterProcesso(Long id) {
        return repository.findById(id)
                .map(this::obterProcessoVo)
                .orElse(null);
    }

    void validarProcesso(ProcessoDadosInclusaoVo dados){
        Norma norma = normaService.obterNorma(dados.getNorma());
        ConsultoriaVo consultoria = consultoriaService.obterConsultoria(dados.getConsultoria());
        if(dados.getConsultoria() != null && consultoria == null) {
            throw new RuntimeException();
        }
        if(norma == null || consultoria.getNormas() == null){
            throw new RuntimeException();
        }
        List<String> consultoriaNormas = consultoria.getNormas().stream()
                .map(ConsultoriaNormaVo::getCodigo)
                .collect(Collectors.toList());
        if(consultoria.getNormas().isEmpty() || !consultoriaNormas.contains(norma.getCodigo())){
            throw new RuntimeException();
        }
    }

    public ProcessoVo criarProcesso(ProcessoDadosInclusaoVo dados) {
        validarProcesso(dados);
        Processo processo = new Processo();
        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        processo = repository.save(processo);
        if(dados.getConsultoria() != null) {
            consultoriaService.propagarProcesso(processo);
        }
        return new ProcessoVo(processo);
    }

    public ProcessoVo atualizarProcesso(Long id, ProcessoDadosInclusaoVo dados) {
        if(dados.getConsultoria() == null) {
            throw new RuntimeException();
        }
        validarProcesso(dados);
        Processo processo = repository.findById(id).orElseThrow(RuntimeException::new);
        boolean associadoConsultoria = processo.getConsultoria() != null;

        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        processo = repository.save(processo);

        if(associadoConsultoria) {
            consultoriaService.atualizarProcesso(processo);
        } else {
            consultoriaService.propagarProcesso(processo);
        }
        return obterProcessoVo(processo);
    }

    private ProcessoVo obterProcessoVo(Processo processo) {
        List<Relatorio> relatorios = relatorioRepository.findByProcesso(processo.getId());
        return new ProcessoVo(processo, relatorios);
    }
}
