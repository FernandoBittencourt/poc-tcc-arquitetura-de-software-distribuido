package com.github.fernandobittencourt.norma.service;

import com.github.fernandobittencourt.norma.domain.Norma;
import com.github.fernandobittencourt.norma.repository.NormaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NormaService {

    @Autowired
    private INormaExternaService normaExternaService;

    @Autowired
    private NormaRepository repository;

    public List<Norma> obterNormas(String codigo) {
        normaExternaService.atualizarNormas(codigo);
        return repository.findByCodigo(codigo);
    }

    public Norma obterNorma(Long id) {
        return repository.findById(id).orElse(null);
    }
}
