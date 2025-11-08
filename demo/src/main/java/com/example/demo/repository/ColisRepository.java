package com.example.demo.repository;

import com.example.demo.entity.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ColisRepository extends JpaRepository<Colis, Long> {
    Page<Colis> findByIdClientExpediteur_IdAndStatutIn(
            Long idClientExpediteur,
            List<String> statuts,
            Pageable pageable);

    /*  Page<Colis> findByIdLivreur(
              Long idLivreur,
              Pageable pageable);*/
    List<Colis> findByIdLivreur_Id(Long idLivreur);
    List<Colis> findByIdDestinataire_Id(Long destinataireId);
    List<Colis> findByIdLivreurIsNull();

    Page<Colis> findByStatutInAndIdZone_IdAndVilleDestinationContainingIgnoreCaseAndPrioriteIn(
            List<String> statuts,
            Long idZone,
            String ville,
            List<String> priorites,
            Pageable pageable
    );

    List<Colis> findByIdZone_NomIgnoreCase(String nomZone);
    List<Colis> findByVilleDestinationIgnoreCase(String villeDestination);

    long countByIdLivreur_Id(Long idLivreur);


    @Query("SELECT COALESCE(SUM(c.poids), 0) FROM Colis c WHERE c.idLivreur.id = :idLivreur")
    BigDecimal getPoidsTotalParLivreur(@Param("idLivreur") Long idLivreur);

}

