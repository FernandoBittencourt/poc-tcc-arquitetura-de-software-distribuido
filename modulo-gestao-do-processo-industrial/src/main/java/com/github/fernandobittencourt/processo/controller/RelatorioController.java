package com.github.fernandobittencourt.processo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fernandobittencourt.processo.domain.Relatorio;
import com.github.fernandobittencourt.processo.domain.vo.RelatorioVo;
import com.github.fernandobittencourt.processo.service.RelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Controller;

@Controller
public class RelatorioController {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);

    @Autowired
    RelatorioService relatorioService;

    @SqsListener(value = "${aws.sqs.endpoint}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receberRelatorios(String stringJson) {
        logger.info("Messagem recebida: " + stringJson);
        ObjectMapper mapper = new ObjectMapper();
        try {
            RelatorioVo relatorioVo = mapper.readValue(stringJson, RelatorioVo.class);
            Relatorio relatorio = relatorioService.incluirRelatorio(relatorioVo);
            logger.info("Relatorio criado: " + relatorio.getId());
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }

}
