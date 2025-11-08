package com.example.demo.dto;

import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
public class ColisAvecZoneDTO {

    private String description;
    private BigDecimal poids;
    private String statut;
    private String priorite;
    private Long idLivreur;
    private Long idClientExpediteur;
    private Long idDestinataire;
    private ZoneAvecColisDTO idZone;
    private String villeDestination;

    // Getter et Setter : description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et Setter : poids
    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    // Getter et Setter : statut
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Getter et Setter : priorite
    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    // Getter et Setter : idLivreur
    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }

    // Getter et Setter : idClientExpediteur
    public Long getIdClientExpediteur() {
        return idClientExpediteur;
    }

    public void setIdClientExpediteur(Long idClientExpediteur) {
        this.idClientExpediteur = idClientExpediteur;
    }

    // Getter et Setter : idDestinataire
    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    // Getter et Setter : idZone
    public ZoneAvecColisDTO getIdZone() {
        return idZone;
    }

    public void setIdZone(ZoneAvecColisDTO idZone) {
        this.idZone = idZone;
    }

    // Getter et Setter : villeDestination
    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }
}
