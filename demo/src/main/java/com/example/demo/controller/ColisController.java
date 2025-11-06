package com.example.demo.controller;

import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.service.ColisService;
import com.example.demo.service.DestinataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colis")
public class ColisController {
    @Autowired
    private ColisService colisService;

    @PostMapping
    public ResponseEntity<ColisDTO> createColis(@RequestBody ColisDTO colisDTO) {
        ColisDTO savedColis = colisService.saveColis(colisDTO);
        return ResponseEntity.ok(savedColis);
    }


    @GetMapping("/client-expediteur/{idClientExpediteur}")
    public ResponseEntity<Page<ColisDTO>> getColisByClient(
            @PathVariable("idClientExpediteur") Long idClientExpediteur,
            Pageable pageable) {

        Page<ColisDTO> pageColis = colisService.getColisByClientAndStatut(idClientExpediteur, pageable);
        return ResponseEntity.ok(pageColis);
    }


    @PutMapping("/colis/{idColis}")
    public String updateColisIdLivreur(
            @PathVariable("idColis") Long idColis,
            @RequestBody UpdateColisDTO colisDTO) {

        colisService.updateIdColis(idColis , colisDTO);
        return "ok";

    }

    @GetMapping("/livreur/{idLivreur}")
    public ResponseEntity<Page<ColisDTO>> getColisByLivreur(
            @PathVariable("idLivreur") Long idLivreur,
            Pageable pageable) {

        Page<ColisDTO> pageColis = colisService.getColisByIdLivreur(idLivreur, pageable);
        return ResponseEntity.ok(pageColis);
    }














    @PutMapping("updateStatut/{idColis}")
    public ResponseEntity<?> updateStatut(@PathVariable ("idColis") Long idColis,
                                          @RequestBody UpdateStautColisDTO colisDTO){

        ColisDTO colisDto= colisService.updateStautColis(idColis,colisDTO);


        return ResponseEntity.ok("updated with success");
    }

















    @GetMapping("colisEnattent")
    public List<ColisDTO> getColisEnAttent(){
        List<ColisDTO> colis= colisService.getColisEnAttent();
        List<ColisDTO> colisIdNull = colis.stream()
                .filter(c->c.getIdLivreur()==null
                )
                .toList();
        return colisIdNull;
    }







































}
