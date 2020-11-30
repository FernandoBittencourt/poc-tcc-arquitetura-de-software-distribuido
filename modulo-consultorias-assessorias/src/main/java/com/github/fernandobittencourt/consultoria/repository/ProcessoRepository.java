package com.github.fernandobittencourt.consultoria.repository;

import com.github.fernandobittencourt.consultoria.domain.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
