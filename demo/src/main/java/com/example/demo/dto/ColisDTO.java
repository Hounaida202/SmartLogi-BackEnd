

package com.example.demo.dto;

import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
public class ColisDTO {

    private String description;
    private BigDecimal poids;
    private String statut;
    private String priorite;
    private Long idLivreur;
    private Long idClientExpediteur;
    private Long idDestinataire;
    private Long idZone;
    private String villeDestination;
    private String nomZone;

    // Getters & Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPoids() { return poids; }
    public void setPoids(BigDecimal poids) { this.poids = poids; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public Long getIdLivreur() { return idLivreur; }
    public void setIdLivreur(Long idLivreur) { this.idLivreur = idLivreur; }

    public Long getIdClientExpediteur() { return idClientExpediteur; }
    public void setIdClientExpediteur(Long idClientExpediteur) { this.idClientExpediteur = idClientExpediteur; }

    public Long getIdDestinataire() { return idDestinataire; }
    public void setIdDestinataire(Long idDestinataire) { this.idDestinataire = idDestinataire; }

    public Long getIdZone() { return idZone; }
    public void setIdZone(Long idZone) { this.idZone = idZone; }

    public String getVilleDestination() { return villeDestination; }
    public void setVilleDestination(String villeDestination) { this.villeDestination = villeDestination; }


    // Getters & Setters
    public String getNomZone() { return nomZone; }
    public void setNomZone(String nomZone) { this.nomZone = nomZone; }
}

