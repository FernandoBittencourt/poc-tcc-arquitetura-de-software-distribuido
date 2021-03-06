package com.github.fernandobittencourt.norma.controller;

import com.github.fernandobittencourt.norma.domain.Norma;
import com.github.fernandobittencourt.norma.service.NormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class NormaController {

    @Autowired
    private NormaService service;

    @GetMapping("/normas")
    public List<Norma> obterNormas(@RequestParam String codigo) {
        return service.obterNormas(codigo);
    }

    @GetMapping("/normas/{id}")
    public Norma obterNorma(@PathVariable Long id) {
        return service.obterNorma(id);
    }

}
