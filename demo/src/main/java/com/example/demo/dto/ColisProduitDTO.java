package com.example.demo.dto;

import java.math.BigDecimal;

public class ColisProduitDTO {

    private Long idColis;
    private Long idProduit;
    private Integer quantite;
    private BigDecimal prix;

    public Long getIdColis() {
        return idColis;
    }

    public void setIdColis(Long idColis) {
        this.idColis = idColis;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
}
