package com.github.fernandobittencourt.processo.repository;

import com.github.fernandobittencourt.processo.domain.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
}
