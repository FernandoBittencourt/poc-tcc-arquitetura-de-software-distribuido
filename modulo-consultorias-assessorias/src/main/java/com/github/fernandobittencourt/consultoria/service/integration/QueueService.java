package com.github.fernandobittencourt.consultoria.service.integration;

import com.github.fernandobittencourt.consultoria.service.IQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class QueueService implements IQueueService {

    private static final Logger logger = LoggerFactory.getLogger(QueueService.class);

    @Value("${aws.sqs.endpoint}")
    private String endpoint;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    public void inserirMensagem(String mensagem) {
        try {
            queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(mensagem).build());
            logger.info("Mensagem enviada com sucesso: " + mensagem);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
