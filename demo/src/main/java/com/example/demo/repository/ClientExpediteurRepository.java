package com.example.demo.repository;

import com.example.demo.entity.ClientExpediteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientExpediteurRepository extends JpaRepository<ClientExpediteur, Long> {

    List<ClientExpediteur> findByNomIgnoreCase(String nom);


}
