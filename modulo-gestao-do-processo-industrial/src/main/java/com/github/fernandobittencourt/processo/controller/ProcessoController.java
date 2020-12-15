package com.github.fernandobittencourt.processo.controller;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.processo.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProcessoController {

    @Autowired
    private ProcessoService service;

    @GetMapping("/processos")
    public List<Processo> obterProcessos() {
        return service.obterProcessos();
    }

    @GetMapping("/processos/{id}")
    public Processo obterProcesso(@PathVariable Long id) {
        return service.obterProcesso(id);
    }

    @PostMapping("/processos")
    public Processo criarProcesso(@RequestBody ProcessoDadosInclusaoVo dados) {
        return service.criarProcesso(dados);
    }

    @PutMapping("/processos/{id}")
    public Processo atualizarProcesso(@PathVariable Long id, @RequestBody ProcessoDadosInclusaoVo dados) {
        return service.atualizarProcesso(id, dados);
    }

}
