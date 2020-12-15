package com.github.fernandobittencourt.consultoria.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.RelatorioVo;
import com.github.fernandobittencourt.consultoria.repository.ArquivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ArquivoService {

    private static final Logger logger = LoggerFactory.getLogger(ArquivoService.class);

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Autowired
    private ArquivoRepository repository;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IQueueService queueService;

    public List<Arquivo> obterArquivos(Long consultoriaId, Long processoId) {
        if(processoService.obterProcesso(consultoriaId, processoId) == null) {
            throw new RuntimeException();
        }
        return repository.findByProcesso(processoId);
    }

    public Arquivo incluirArquivo(Long consultoriaId, Long processoId, MultipartFile dados) {
        Processo processo = processoService.obterProcesso(consultoriaId, processoId);
        if(processo == null) {
            throw new RuntimeException();
        }
        try {
            String link = storageService.armazenarArquivo(dados);
            Arquivo arquivo = criarArquivo(dados, processo, link);
            queueService.inserirMensagem(criarMensagem(arquivo));
            return arquivo;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String criarMensagem(Arquivo arquivo){
        try {
            RelatorioVo relatorioVo = new RelatorioVo(arquivo);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(relatorioVo);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private Arquivo criarArquivo(MultipartFile dados, Processo processo, String link) {
        Arquivo arquivo = new Arquivo();
        arquivo.setName(dados.getOriginalFilename());
        arquivo.setData(new Date());
        arquivo.setProcesso(processo);
        arquivo.setBucket(bucket);
        arquivo.setLink(link);
        repository.save(arquivo);
        return arquivo;
    }
}
