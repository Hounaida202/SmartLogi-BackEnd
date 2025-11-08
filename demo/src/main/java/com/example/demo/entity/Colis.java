package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "colis")
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    @Size(max = 255, message = "La description ne doit pas dépasser 255 caractères")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Le poids est obligatoire")
    @DecimalMin(value = "0.01", message = "Le poids doit être supérieur à 0")
    @Digits(integer = 8, fraction = 2, message = "Poids invalide")
    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @NotBlank(message = "Le statut est obligatoire")
    @Size(max = 50, message = "Le statut ne doit pas dépasser 50 caractères")
    @Column(name = "statut", length = 50)
    private String statut;

    @NotBlank(message = "La priorité est obligatoire")
    @Pattern(regexp = "P1|P2|P3", message = "La priorité doit être P1, P2 ou P3")
    @Column(name = "priorite", length = 20)
    private String priorite;

    @NotNull(message = "Le livreur est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livreur")
    private Livreur idLivreur;

    @NotNull(message = "Le client expéditeur est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_expediteur")
    private ClientExpediteur idClientExpediteur;

    @NotNull(message = "Le destinataire est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinataire")
    private Destinataire idDestinataire;

    @NotNull(message = "La zone est obligatoire")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_zone")
    private Zone idZone;

    @NotBlank(message = "La ville de destination est obligatoire")
    @Size(max = 100, message = "La ville de destination ne doit pas dépasser 100 caractères")
    @Column(name = "ville_destination", length = 100)
    private String villeDestination;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPoids() { return poids; }
    public void setPoids(BigDecimal poids) { this.poids = poids; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public Livreur getIdLivreur() { return idLivreur; }
    public void setIdLivreur(Livreur idLivreur) { this.idLivreur = idLivreur; }

    public ClientExpediteur getIdClientExpediteur() { return idClientExpediteur; }
    public void setIdClientExpediteur(ClientExpediteur idClientExpediteur) { this.idClientExpediteur = idClientExpediteur; }

    public Destinataire getIdDestinataire() { return idDestinataire; }
    public void setIdDestinataire(Destinataire idDestinataire) { this.idDestinataire = idDestinataire; }

    public Zone getIdZone() { return idZone; }
    public void setIdZone(Zone idZone) { this.idZone = idZone; }

    public String getVilleDestination() { return villeDestination; }
    public void setVilleDestination(String villeDestination) { this.villeDestination = villeDestination; }
}
