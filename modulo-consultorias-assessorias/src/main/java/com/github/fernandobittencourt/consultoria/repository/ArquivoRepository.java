package com.github.fernandobittencourt.consultoria.repository;

import com.github.fernandobittencourt.consultoria.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    @Query("SELECT a FROM Arquivo a WHERE a.processo.id = ?1")
    List<Arquivo> findByProcesso(Long processo);
}
