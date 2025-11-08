package com.example.demo.controller;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
//import com.example.demo.service.ColisService;
import com.example.demo.service.ColisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

//    -------------------------------------------

    @PostMapping("saveColis")
    public ResponseEntity<ColisDTO> createColis(@RequestBody ColisDTO colisDTO) {
        ColisDTO savedColis = colisService.saveColis(colisDTO);
        return ResponseEntity.ok(savedColis);
    }

//    -------------------------------------------------

    @PostMapping("save-avec-zone")
    public ResponseEntity<?> heyhey(
            @RequestBody ColisAvecZoneDTO colisDTO){

        ColisAvecZoneDTO colisDto= colisService.creerColisAvecZone(colisDTO);


        return ResponseEntity.ok("created with success");
    }

//    -------------------------------------------------

    @GetMapping("/client-expediteur/{idClientExpediteur}")
    public ResponseEntity<Page<ColisDTO>> getColisByClient(
            @PathVariable("idClientExpediteur") Long idClientExpediteur,
            Pageable pageable) {

        Page<ColisDTO> pageColis = colisService.getColisByClientAndStatut(idClientExpediteur, pageable);
        return ResponseEntity.ok(pageColis);
    }

    //    -------------------------------------------------
    @GetMapping("/destinataire/{id}/statuts")
    public List<String> getStatutParDestinataire(@PathVariable Long id) {
        return colisService.getStatutColisParDestinataire(id);
    }

//--------------------------------------

    @GetMapping("/livreur/{id}")
    public List<ColisAvecZoneDTO> getColisParLivreur(@PathVariable Long id) {
        return colisService.getColisAvecZoneParLivreur(id);
    }

//    --------------------------------------

    @PutMapping("updateStatut/{idColis}")
    public ResponseEntity<?> updateStatut(@PathVariable ("idColis") Long idColis,
                                          @RequestBody UpdateStautColisDTO colisDTO){

        ColisDTO colisDto= colisService.updateStautColis(idColis,colisDTO);


        return ResponseEntity.ok("updated with success");
    }

//    ------------------------------------

    @GetMapping("/sans-livreur")
    public List<ColisAvecZoneDTO> getColisSansLivreur() {
        return colisService.getColisSansLivreur();
    }

    //    ------------------------------------

    @PutMapping("/{idColis}/{idLivreur}")
    public ColisAvecZoneDTO assignerLivreur(
            @PathVariable Long idColis,
            @PathVariable Long idLivreur) {
        return colisService.assignerLivreurAuColis(idColis, idLivreur);
    }

    //    ------------------------------------

    @GetMapping("/filtrer")
    public Page<ColisAvecZoneDTO> filtrerColis(
            @RequestParam List<String> statuts,
            @RequestParam Long idZone,
            @RequestParam(required = false) String ville,
            @RequestParam List<String> priorites,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return colisService.getColisFiltres(statuts, idZone, ville, priorites, pageable);
    }
    //localhost:8080/coliss/filtrer?statuts=crée&idZone=1&ville=Casablanca&priorites=Moyenne&page=0&size=5

    //    ------------------------------------

    @GetMapping("/zone")
    public List<ColisAvecZoneDTO> getColisParNomZone(@RequestParam String nomZone) {
        return colisService.getColisParNomZone(nomZone);
    }

    //    ------------------------------------

    @GetMapping("/ville")
    public List<ColisAvecZoneDTO> getColisParVille(@RequestParam String ville) {
        return colisService.getColisParVille(ville);
    }

    //    ------------------------------------

    @GetMapping("/nombre/livreur/{idLivreur}")
    public long getNombreColisParLivreur(@PathVariable Long idLivreur) {
        return colisService.getNombreTotalColisParLivreur(idLivreur);
    }

    //    ------------------------------------

    @GetMapping("/poids/livreur/{idLivreur}")
    public BigDecimal getPoidsTotalColisParLivreur(@PathVariable Long idLivreur) {
        return colisService.getPoidsTotalParLivreur(idLivreur);
    }
}
