package com.github.fernandobittencourt.consultoria.service;

import com.github.fernandobittencourt.consultoria.domain.Norma;

import java.util.List;

public interface INormaService {

    List<Norma> obterNormas(List<String> normas);
}
