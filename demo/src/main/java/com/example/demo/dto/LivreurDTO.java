package com.example.demo.dto;

public class LivreurDTO {
    private String nom;
    private String prenom;
    private String telephone;
    private String vehicule;
    private String zoneAssignee;

    public LivreurDTO(){}

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getVehicule(){
        return vehicule;
    }
    public String getZoneAssignee(){
        return zoneAssignee;
    }


    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public void setVehicule(String vehicule){
        this.vehicule=vehicule;
    }
    public void setZoneAssignee(String zoneAssignee){
        this.zoneAssignee=zoneAssignee;
    }

}