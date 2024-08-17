package com.mrafp.idarati.Controller;


import com.mrafp.idarati.Services.DossierService;
import com.mrafp.idarati.Dto.DossierDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    private final DossierService dossierService;

    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping
    public ResponseEntity<List<DossierDto>> getAllDossiers() {
        List<DossierDto> dossiers = dossierService.getAllDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierDto> getDossierById(@PathVariable("id") Long id) {
        Optional<DossierDto> dossier = dossierService.getDossierById(id);
        return dossier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/procedure/{procedureId}")
    public ResponseEntity<List<DossierDto>> getDossiersByProcedureId(@PathVariable("procedureId") Long procedureId) {
        List<DossierDto> dossiers = dossierService.getDossiersByProcedureId(procedureId);
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/condition/{conditionId}")
    public ResponseEntity<List<DossierDto>> getDossiersByConditionId(@PathVariable("conditionId") Long conditionId) {
        List<DossierDto> dossiers = dossierService.getDossiersByConditionId(conditionId);
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<DossierDto> saveDossier(@RequestBody DossierDto dossierDto) {
        DossierDto savedDossier = dossierService.saveDossier(dossierDto);
        return new ResponseEntity<>(savedDossier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierDto> updateDossier(@PathVariable("id") Long id, @RequestBody DossierDto dossierDto) {
        try {
            DossierDto updatedDossier = dossierService.updateDossier(id, dossierDto);
            return new ResponseEntity<>(updatedDossier, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable("id") Long id) {
        try {
            dossierService.deleteDossier(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}