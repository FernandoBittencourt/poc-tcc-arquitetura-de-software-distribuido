package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.repository.ArquivoRepository;
import com.github.fernandobittencourt.consultoria.repository.ConsultoriaRepository;
import com.github.fernandobittencourt.consultoria.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository repository;

    @Autowired
    private ProcessoService processoService;

    @Autowired IStorageService storageService;

    public List<Arquivo> obterArquivos(Long consultoriaId, Long processoId) {
        if(processoService.obterProcesso(consultoriaId, processoId) == null) {
            throw new RuntimeException();
        }
        return repository.findByProcesso(processoId);
    }

    public Arquivo incluirArquivo(Long consultoriaId, Long processoId) {
        Processo processo = processoService.obterProcesso(consultoriaId, processoId);
        if(processo == null) {
            throw new RuntimeException();
        }
        String link = storageService.armazenarArquivo();
        Arquivo arquivo = new Arquivo();
        arquivo.setData(new Date());
        arquivo.setProcesso(processo);
        arquivo.setLink(link);
        repository.save(arquivo);
        return null;
    }
}
