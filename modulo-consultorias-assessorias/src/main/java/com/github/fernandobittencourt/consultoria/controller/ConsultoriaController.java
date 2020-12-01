package com.github.fernandobittencourt.consultoria.controller;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.service.ArquivoService;
import com.github.fernandobittencourt.consultoria.service.ConsultoriaService;
import com.github.fernandobittencourt.consultoria.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController(value = "/consultorias")
public class ConsultoriaController {

    @Autowired
    private ConsultoriaService consultoriaService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ArquivoService arquivoService;

    @GetMapping
    public List<Consultoria> obterConsultorias() {
        return consultoriaService.obterConsultorias();
    }

    @GetMapping("/{id}")
    public Consultoria obterConsultoria(@PathVariable Long id) {
        return consultoriaService.obterConsultoria(id);
    }

    @PostMapping
    public Consultoria criarConsultoria(@RequestBody ConsultoriaDadosInclusaoVo dados) {
        return consultoriaService.criarConsultoria(dados);
    }

    @PutMapping("/{id}")
    public Consultoria atualizarConsultoria(@PathVariable Long id, @RequestBody ConsultoriaDadosInclusaoVo dados) {
        return consultoriaService.atualizarConsultoria(id, dados);
    }


    @GetMapping("/{consultoria}/processos")
    public List<Processo> obterProcessos(@PathVariable Long consultoria, @PathVariable Long processo) {
        return processoService.obterProcessosPorConsultoria(consultoria);
    }

    @GetMapping("/{consultoria}/processos/{processo}")
    public Processo obterProcesso(@PathVariable Long consultoria, @PathVariable Long processo) {
        return processoService.obterProcesso(consultoria, processo);
    }

    @GetMapping("/consultorias/{consultoria}/processo/{processo}/documentos")
    public List<Arquivo> obterArquivos(@PathVariable Long consultoria, @PathVariable Long processo) {
        return arquivoService.obterArquivos(consultoria, processo);
    }

    @PostMapping("/consultorias/{consultoria}/processo/{processo}/documentos")
    public Arquivo incluirArquivo(@PathVariable Long consultoria, @PathVariable Long processo) {
        return arquivoService.incluirArquivo(consultoria, processo);
    }
}
