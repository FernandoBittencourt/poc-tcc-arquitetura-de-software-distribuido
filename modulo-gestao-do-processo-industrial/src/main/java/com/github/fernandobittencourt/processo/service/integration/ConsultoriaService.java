package com.github.fernandobittencourt.processo.service.integration;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.vo.ConsultoriaVo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoInclusaoConsultoriaVo;
import com.github.fernandobittencourt.processo.service.IConsultoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultoriaService implements IConsultoriaService {

    @Value("${consultoria.endpoint}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ConsultoriaVo obterConsultoria(Long id) {
        String url = endpoint + "/" + id.toString();
        ResponseEntity<ConsultoriaVo> responseEntity = restTemplate.getForEntity(url, ConsultoriaVo.class);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null) {
            return responseEntity.getBody();
        }
        return null;
    }

    @Override
    public void propagarProcesso(Processo processo) {
        ProcessoInclusaoConsultoriaVo vo = new ProcessoInclusaoConsultoriaVo(processo);
        String url = endpoint + "/{consultoria}/processos";
        Map<String, String> param = new HashMap<>();
        param.put("consultoria", processo.getConsultoria().toString());

        ResponseEntity resultado = restTemplate.postForEntity(url,vo, Object.class, param);
        if(!HttpStatus.OK.equals(resultado.getStatusCode())) {
            throw new RuntimeException();
        }
    }

    @Override
    public void atualizarProcesso(Processo processo) {
        String url = endpoint + "/{consultoria}/processos";
        Map<String, String> param = new HashMap<>();
        param.put("consultoria", processo.getConsultoria().toString());
        HttpEntity<ProcessoInclusaoConsultoriaVo> requestEntity = new HttpEntity<>(new ProcessoInclusaoConsultoriaVo(processo));

        ResponseEntity resultado = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Object.class, param);
        if(!HttpStatus.OK.equals(resultado.getStatusCode())) {
            throw new RuntimeException();
        }
    }
}
