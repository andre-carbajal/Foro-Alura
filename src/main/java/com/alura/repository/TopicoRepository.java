package com.alura.repository;

import com.alura.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByActivoTrue(Pageable paginacion);
    boolean existsByTitulo(String titulo);
}
