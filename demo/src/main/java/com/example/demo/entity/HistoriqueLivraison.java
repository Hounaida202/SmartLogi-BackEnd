package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "historique_livraison")
public class HistoriqueLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_colis")
    private Colis colis;

    @Size(max = 50)
    private String statut;

    private LocalDateTime dateChangement;

    @Size(max = 255)
    private String commentaire;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Colis getColis() { return colis; }
    public void setColis(Colis colis) { this.colis = colis; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateChangement() { return dateChangement; }
    public void setDateChangement(LocalDateTime dateChangement) { this.dateChangement = dateChangement; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
