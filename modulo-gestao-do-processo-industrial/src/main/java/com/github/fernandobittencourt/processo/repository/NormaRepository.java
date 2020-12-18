package com.github.fernandobittencourt.processo.repository;

import com.github.fernandobittencourt.processo.domain.Norma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormaRepository extends JpaRepository<Norma, Long> {
    List<Norma> findByCodigo(String codigo);
}

