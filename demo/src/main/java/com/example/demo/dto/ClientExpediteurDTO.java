package com.example.demo.dto;

public class ClientExpediteurDTO {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;

    public ClientExpediteurDTO(){}


    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getEmail(){
        return email;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getAdresse(){
        return adresse;
    }

    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public void setAdresse(String adresse){
        this.adresse=adresse;
    }

}
