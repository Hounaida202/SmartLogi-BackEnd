package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name="historique_livraison")
public class HistoriqueLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_colis", nullable = false)
    private Colis idColis;

    @Size(max = 50)
    @Column(name = "statut", length = 50)
    private String statut;

    @ColumnDefault("now()")
    @Column(name = "date_changement")
    private Instant dateChangement;

    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Instant getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Instant dateChangement) {
        this.dateChangement = dateChangement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Colis getIdColis() {
        return idColis;
    }

    public void setIdColis(Colis idColis) {
        this.idColis = idColis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
