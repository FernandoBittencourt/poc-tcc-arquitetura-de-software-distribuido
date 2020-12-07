package com.github.fernandobittencourt.consultoria.service.integration;

import com.github.fernandobittencourt.consultoria.domain.Norma;
import com.github.fernandobittencourt.consultoria.repository.NormaRepository;
import com.github.fernandobittencourt.consultoria.service.INormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormaService implements INormaService {

    @Autowired
    private NormaRepository repository;

    @Override
    public List<Norma> obterNormas(List<String> normas) {
        return normas.stream().map(this::obterNorma).collect(Collectors.toList());
    }

    private Norma obterNorma(String codigo) {
        Norma norma = obterNormaLocal(codigo);
        if(norma == null) {
            norma = obterNormaServicoExterno(codigo);
        }
        if(norma == null) {
            throw new RuntimeException();
        }
        return norma;
    }

    private Norma obterNormaLocal(String norma){
        return repository.findByCodigo(norma).stream().findAny().orElse(null);
    }

    private Norma obterNormaServicoExterno(String codigo){
        //TODO: Integrar com o modulo de normas.
        Long idExterno = 1L;
        //TODO: Salvar Norma na base
        Norma norma = new Norma();
        norma.setCodigo(codigo);
        norma.setExternoId(idExterno);

        return repository.save(norma);
    }
}
