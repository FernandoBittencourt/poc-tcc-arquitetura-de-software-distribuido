package com.github.fernandobittencourt.norma.service.integration;

import com.github.fernandobittencourt.norma.repository.NormaRepository;
import com.github.fernandobittencourt.norma.service.INormaExternaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormaExternaService implements INormaExternaService {

    @Autowired
    private NormaRepository repository;

    @Override
    public void atualizarNormas(String codigo) {
        //TODO: Realizar a integração com o repositorio externo
    }
}
