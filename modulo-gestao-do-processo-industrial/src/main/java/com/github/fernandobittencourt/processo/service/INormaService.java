package com.github.fernandobittencourt.processo.service;

import com.github.fernandobittencourt.processo.domain.Norma;

public interface INormaService {
    Norma obterNorma(String codigo);
}
