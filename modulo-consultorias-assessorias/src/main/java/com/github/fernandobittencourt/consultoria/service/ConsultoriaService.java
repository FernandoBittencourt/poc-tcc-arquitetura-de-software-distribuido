package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Norma;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.repository.ConsultoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultoriaService {

    @Autowired
    private INormaService normaService;

    @Autowired
    private ConsultoriaRepository repository;

    public List<Consultoria> obterConsultorias() {
        return repository.findAll();
    }

    public Consultoria obterConsultoria(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Consultoria criarConsultoria(ConsultoriaDadosInclusaoVo dados) {
        List<Norma> normas = normaService.obterNormas(dados.getNormas());
        Consultoria consultoria = new Consultoria();
        consultoria.setNome(dados.getNome());
        consultoria.setNormas(normas);
        consultoria.setStatus(dados.getStatus());
        return repository.save(consultoria);
    }

    public Consultoria atualizarConsultoria(Long id, ConsultoriaDadosInclusaoVo dados) {
        List<Norma> normas = normaService.obterNormas(dados.getNormas());
        Consultoria consultoria = repository.findById(id).orElseThrow(RuntimeException::new);
        consultoria.setNome(dados.getNome());
        consultoria.setNormas(normas);
        consultoria.setStatus(dados.getStatus());
        return repository.save(consultoria);
    }

}
