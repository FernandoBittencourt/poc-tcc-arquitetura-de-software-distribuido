package com.github.fernandobittencourt.consultoria.repository;

import com.github.fernandobittencourt.consultoria.domain.Consultoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultoriaRepository extends JpaRepository<Consultoria, Long> {

}
