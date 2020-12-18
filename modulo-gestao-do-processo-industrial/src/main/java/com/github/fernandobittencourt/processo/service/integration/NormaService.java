package com.github.fernandobittencourt.processo.service.integration;

import com.github.fernandobittencourt.processo.domain.Norma;
import com.github.fernandobittencourt.processo.domain.vo.NormaVo;
import com.github.fernandobittencourt.processo.repository.NormaRepository;
import com.github.fernandobittencourt.processo.service.INormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormaService implements INormaService {

    @Value("${norma.endpoint}")
    private String endpoint;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NormaRepository repository;

    @Override
    public Norma obterNorma(String codigo) {
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
        String url = UriComponentsBuilder
                .fromHttpUrl(endpoint)
                .queryParam("codigo", codigo)
                .toUriString();
        ResponseEntity<NormaVo[]> responseEntity = restTemplate.getForEntity(url, NormaVo[].class);
        if(HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null){
            NormaVo vo = responseEntity.getBody()[0];
            Norma norma = new Norma();
            norma.setCodigo(vo.getCodigo());
            return repository.save(norma);
        }
        return null;
    }
}
