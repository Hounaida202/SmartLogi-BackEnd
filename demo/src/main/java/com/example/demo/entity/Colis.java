package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name="colis")
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @Size(max = 50)
    @Column(name = "statut", length = 50)
    private String statut;

    @Size(max = 20)
    @Column(name = "priorite", length = 20)
    private String priorite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livreur")
    private Livreur idLivreur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_expediteur")
    private ClientExpediteur idClientExpediteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinataire")
    private Destinataire idDestinataire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zone")
    private Zone idZone;

    @Size(max = 100)
    @Column(name = "ville_destination", length = 100)
    private String villeDestination;

    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    public Zone getIdZone() {
        return idZone;
    }

    public void setIdZone(Zone idZone) {
        this.idZone = idZone;
    }

    public Destinataire getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Destinataire idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public ClientExpediteur getIdClientExpediteur() {
        return idClientExpediteur;
    }

    public void setIdClientExpediteur(ClientExpediteur idClientExpediteur) {
        this.idClientExpediteur = idClientExpediteur;
    }

    public Livreur getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Livreur idLivreur) {
        this.idLivreur = idLivreur;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
