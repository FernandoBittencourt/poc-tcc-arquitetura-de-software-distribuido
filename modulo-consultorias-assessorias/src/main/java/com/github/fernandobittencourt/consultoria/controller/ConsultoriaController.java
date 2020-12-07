package com.github.fernandobittencourt.consultoria.controller;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import com.github.fernandobittencourt.consultoria.domain.vo.ConsultoriaDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.domain.vo.ProcessoDadosInclusaoVo;
import com.github.fernandobittencourt.consultoria.service.ArquivoService;
import com.github.fernandobittencourt.consultoria.service.ConsultoriaService;
import com.github.fernandobittencourt.consultoria.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class ConsultoriaController {

    @Autowired
    private ConsultoriaService consultoriaService;

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ArquivoService arquivoService;

    @GetMapping("/consultorias")
    public List<Consultoria> obterConsultorias() {
        return consultoriaService.obterConsultorias();
    }

    @GetMapping("/consultorias/{id}")
    public Consultoria obterConsultoria(@PathVariable Long id) {
        return consultoriaService.obterConsultoria(id);
    }

    @PostMapping("/consultorias")
    public Consultoria criarConsultoria(@RequestBody ConsultoriaDadosInclusaoVo dados) {
        return consultoriaService.criarConsultoria(dados);
    }

    @PutMapping("/consultorias/{id}")
    public Consultoria atualizarConsultoria(@PathVariable Long id, @RequestBody ConsultoriaDadosInclusaoVo dados) {
        return consultoriaService.atualizarConsultoria(id, dados);
    }

    @GetMapping("/consultorias/{consultoria}/processos")
    public List<Processo> obterProcessos(@PathVariable Long consultoria) {
        return processoService.obterProcessosPorConsultoria(consultoria);
    }

    @PostMapping("/consultorias/{consultoria}/processos")
    public Processo incluirProcesso(@PathVariable Long consultoria, ProcessoDadosInclusaoVo dados) {
        return processoService.incluirProcesso(consultoria, dados);
    }

    @PutMapping("/consultorias/{consultoria}/processos/{processo}")
    public Processo atualizarProcesso(@PathVariable Long consultoria, Long processo, ProcessoDadosInclusaoVo dados) {
        return processoService.atualizarProcesso(consultoria, processo, dados);
    }

    @GetMapping("/consultorias/{consultoria}/processos/{processo}")
    public Processo obterProcesso(@PathVariable Long consultoria, @PathVariable Long processo) {
        return processoService.obterProcesso(consultoria, processo);
    }

    @GetMapping("/consultorias/consultorias/{consultoria}/processo/{processo}/documentos")
    public List<Arquivo> obterArquivos(@PathVariable Long consultoria, @PathVariable Long processo) {
        return arquivoService.obterArquivos(consultoria, processo);
    }

    @PostMapping("/consultorias/{consultoria}/processo/{processo}/documentos")
    public Arquivo incluirArquivo(@PathVariable Long consultoria, @PathVariable Long processo, @RequestParam MultipartFile arquivo) {
        return arquivoService.incluirArquivo(consultoria, processo, arquivo);
    }
}
