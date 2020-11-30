package com.github.fernandobittencourt.processo.repository;

import com.github.fernandobittencourt.processo.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    List<Processo> findByCodigo(String codigo);
}
