package com.github.fernandobittencourt.norma.repository;

import com.github.fernandobittencourt.norma.domain.Norma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormaRepository extends JpaRepository<Norma, Long> {
    List<Norma> findByCodigo(String codigo);

    List<Norma> findByCodigoAndVersao(String codigo, String versao);
}
