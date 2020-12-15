package com.github.fernandobittencourt.processo.repository;

import com.github.fernandobittencourt.processo.domain.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
    @Query("SELECT r FROM Relatorio r WHERE r.processo.id = ?1")
    List<Relatorio> findByProcesso(Long processo);
}
