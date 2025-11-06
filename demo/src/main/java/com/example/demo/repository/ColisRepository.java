package com.example.demo.repository;

import com.example.demo.entity.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColisRepository extends JpaRepository<Colis, Long> {
    Page<Colis> findByIdClientExpediteur_IdAndStatutIn(
            Long idClientExpediteur,
            List<String> statuts,
            Pageable pageable);

    /*  Page<Colis> findByIdLivreur(
              Long idLivreur,
              Pageable pageable);*/
    Page<Colis> findByIdLivreur_Id(Long idLivreur, Pageable pageable);

}

