package com.example.demo.constants;

/**
 * Constantes pour l'entité Colis
 * Ces constantes assurent la cohérence des valeurs utilisées dans l'application
 */
public final class ColisConstants {

    private ColisConstants() {
        // Constructeur privé pour empêcher l'instanciation
    }

    /**
     * Priorités disponibles pour un colis
     * Correspondent au pattern de validation : ^(P1|P2|P3)$
     */
    public static final class Priorite {
        public static final String P1 = "P1";  // Priorité haute
        public static final String P2 = "P2";  // Priorité moyenne
        public static final String P3 = "P3";  // Priorité basse

        private Priorite() {}
    }

    /**
     * Statuts disponibles pour un colis
     */
    public static final class Statut {
        public static final String CREE = "crée";
        public static final String COLLECTE = "collecté";
        public static final String EN_STOCK = "en stock";
        public static final String EN_TRANSIT = "en transit";
        public static final String LIVRE = "livré";

        private Statut() {}
    }
}