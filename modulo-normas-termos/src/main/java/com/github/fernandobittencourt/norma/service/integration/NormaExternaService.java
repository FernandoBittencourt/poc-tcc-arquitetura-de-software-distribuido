package com.github.fernandobittencourt.norma.service.integration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fernandobittencourt.norma.domain.Norma;
import com.github.fernandobittencourt.norma.domain.vo.NormaExternaConsultaDadosVo;
import com.github.fernandobittencourt.norma.domain.vo.NormaExternaVo;
import com.github.fernandobittencourt.norma.domain.vo.ResultadoNormaExternaVo;
import com.github.fernandobittencourt.norma.repository.NormaRepository;
import com.github.fernandobittencourt.norma.service.INormaExternaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class NormaExternaService implements INormaExternaService {

    private static final Logger logger = LoggerFactory.getLogger(NormaExternaService.class);

    @Value("${aws.lambda.name}")
    private String functionName;

    @Value("${aws.lambda.region}")
    private String region;

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;

    @Autowired
    private NormaRepository repository;

    @Override
    public void atualizarNormas(String codigo) {

        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(obterPayload(codigo));

        try {
            AWSLambda awsLambda = criarAwsLambda();
            InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
            NormaExternaVo normaExterna = obterNormaExterna(invokeResult);

            List<Norma> normas = repository.findByCodigoAndVersao(normaExterna.getCodigo(), normaExterna.getVersao());
            if(normas == null || normas.isEmpty()) {
                Norma norma = new Norma();
                norma.setCodigo(normaExterna.getCodigo());
                norma.setConteudo(normaExterna.getConteudo());
                norma.setVersao(normaExterna.getVersao());
                repository.save(norma);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private AWSLambda criarAwsLambda() {
        AWSCredentials credentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        return AWSLambdaClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    private NormaExternaVo obterNormaExterna(InvokeResult invokeResult){
        String resposta = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);
        logger.info("Resultado da Lambda: "+resposta);

        ObjectMapper mapper = new ObjectMapper();
        try {
            ResultadoNormaExternaVo relatorioVo = mapper.readValue(resposta, ResultadoNormaExternaVo.class);
            return mapper.readValue(relatorioVo.getBody(), NormaExternaVo.class);

        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    private String obterPayload(String codigo){
        try {
            NormaExternaConsultaDadosVo vo = new NormaExternaConsultaDadosVo(codigo);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(vo);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}
