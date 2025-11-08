package com.example.demo.repository;

import com.example.demo.entity.HistoriqueLivraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueLivraisonRepository extends JpaRepository<HistoriqueLivraison, Long> {
}
