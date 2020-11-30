package com.github.fernandobittencourt.norma.controller;

import com.github.fernandobittencourt.norma.domain.Norma;
import com.github.fernandobittencourt.norma.service.NormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController(value = "/example")
public class NormaController {

    @Autowired
    private NormaService service;

    @GetMapping
    public List<Norma> obterNormas(@RequestParam String codigo) {
        return service.obterNormas(codigo);
    }

    @GetMapping("/{id}")
    public Norma getExample(@PathVariable Long id) {
        return service.obterNorma(id);
    }

}
