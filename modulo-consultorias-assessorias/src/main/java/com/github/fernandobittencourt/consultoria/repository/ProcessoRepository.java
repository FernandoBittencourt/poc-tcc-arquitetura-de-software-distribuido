package com.github.fernandobittencourt.consultoria.repository;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import com.github.fernandobittencourt.consultoria.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    @Query("SELECT p FROM Processo p WHERE p.consultoria.id = ?1")
    List<Processo> findByConsultoria(Long consultoria);
}
