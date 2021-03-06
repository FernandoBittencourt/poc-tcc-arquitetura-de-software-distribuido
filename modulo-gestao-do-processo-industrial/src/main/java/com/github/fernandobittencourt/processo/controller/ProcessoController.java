package com.github.fernandobittencourt.processo.controller;

import com.github.fernandobittencourt.processo.domain.Processo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.processo.domain.vo.ProcessoVo;
import com.github.fernandobittencourt.processo.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProcessoController {

    @Autowired
    private ProcessoService service;

    @GetMapping("/processos")
    public List<ProcessoVo> obterProcessos() {
        return service.obterProcessos();
    }

    @GetMapping("/processos/{id}")
    public ProcessoVo obterProcesso(@PathVariable Long id) {
        return service.obterProcesso(id);
    }

    @PostMapping("/processos")
    public ProcessoVo criarProcesso(@RequestBody ProcessoDadosInclusaoVo dados) {
        return service.criarProcesso(dados);
    }

    @PutMapping("/processos/{id}")
    public ProcessoVo atualizarProcesso(@PathVariable Long id, @RequestBody ProcessoDadosInclusaoVo dados) {
        return service.atualizarProcesso(id, dados);
    }

}
