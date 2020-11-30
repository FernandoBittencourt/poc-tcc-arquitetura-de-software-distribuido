package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
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
    private IArquivoService consultoriaService;

    @Autowired
    private ConsultoriaRepository repository;

    public List<Consultoria> obterConsultorias() {
        return repository.findAll();
    }

    public Consultoria obterConsultoria(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Consultoria criarConsultoria(ConsultoriaDadosInclusaoVo dados) {
        Consultoria consultoria = new Consultoria();
        consultoria.setNome(dados.getNome());
        consultoria.setNormas(dados.getNormas());
        consultoria.setStatus(dados.getStatus());
        return repository.save(consultoria);
    }

    private void validarProcesso(ConsultoriaDadosInclusaoVo dados) {
        //TODO: Validar consultoria
    }

    public Consultoria atualizarConsultoria(Long id, ConsultoriaDadosInclusaoVo dados) {
        validarProcesso(dados);
        Consultoria consultoria = repository.findById(id).orElseThrow(RuntimeException::new); //TODO: Criar exception especifica
        consultoria.setNome(dados.getNome());
        consultoria.setNormas(dados.getNormas());
        consultoria.setStatus(dados.getStatus());
        return repository.save(consultoria);
    }

}
