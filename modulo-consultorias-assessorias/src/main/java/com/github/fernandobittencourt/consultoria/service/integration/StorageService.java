package com.github.fernandobittencourt.consultoria.service.integration;

import com.github.fernandobittencourt.consultoria.service.IStorageService;
import org.springframework.stereotype.Service;

@Service
public class StorageService implements IStorageService {

    @Override
    public String armazenarArquivo() {
        return "Link do arquivo para download"; //TODO: Implementar
    }
}
