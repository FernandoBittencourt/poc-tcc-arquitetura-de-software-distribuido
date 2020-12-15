package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.Relatorio;
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

    private ProcessoVo obterProcessoVo(Processo processo) {
        List<Relatorio> relatorios = relatorioRepository.findByProcesso(processo.getId());
        return new ProcessoVo(processo, relatorios);
    }

    public ProcessoVo obterProcesso(Long id) {
        return repository.findById(id)
                .map(this::obterProcessoVo)
                .orElse(null);
    }

    public ProcessoVo criarProcesso(ProcessoDadosInclusaoVo dados) {
        //TODO: propagar atualização para consultoria
        Processo processo = new Processo();
        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        processo = repository.save(processo);
        return new ProcessoVo(processo);
    }


    public ProcessoVo atualizarProcesso(Long id, ProcessoDadosInclusaoVo dados) {
        //TODO: propagar atualização para consultoria
        Processo processo = repository.findById(id).orElseThrow(RuntimeException::new); //TODO: Criar exception especifica
        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        processo = repository.save(processo);
        List<Relatorio> relatorios = relatorioRepository.findByProcesso(processo.getId());
        return new ProcessoVo(processo, relatorios);
    }
}
