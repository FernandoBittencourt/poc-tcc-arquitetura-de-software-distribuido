package com.github.fernandobittencourt.consultoria.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    String armazenarArquivo(MultipartFile arquivo);
}
