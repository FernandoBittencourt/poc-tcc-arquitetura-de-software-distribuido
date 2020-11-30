package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.processo.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private INormaService normaService;

    @Autowired
    private IConsultoriaService consultoriaService;

    @Autowired
    private ProcessoRepository repository;

    public List<Processo> obterProcessos() {
        return repository.findAll();
    }

    public Processo obterProcesso(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Processo criarProcesso(ProcessoDadosInclusaoVo dados) {
        //TODO: propagar atualização para consultoria
        validarProcesso(dados);
        Processo processo = new Processo();
        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        return repository.save(processo);
    }

    private void validarProcesso(ProcessoDadosInclusaoVo dados) {
        //TODO: Validar consultoria
    }

    public Processo atualizarProcesso(Long id, ProcessoDadosInclusaoVo dados) {
        //TODO: propagar atualização para consultoria
        validarProcesso(dados);
        Processo processo = repository.findById(id).orElseThrow(RuntimeException::new); //TODO: Criar exception especifica
        processo.setArea(dados.getArea());
        processo.setConsultoria(dados.getConsultoria());
        processo.setDados(dados.getDados());
        return repository.save(processo);
    }

}
