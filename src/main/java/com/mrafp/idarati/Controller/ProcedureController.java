package com.mrafp.idarati.Controller;


import com.mrafp.idarati.Services.ProcedureService;
import com.mrafp.idarati.Dto.ProcedureDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping
    public ResponseEntity<List<ProcedureDto>> getAllProcedures() {
        List<ProcedureDto> procedures = procedureService.getAllProcedures();
        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDto> getProcedureById(@PathVariable("id") Long id) {
        Optional<ProcedureDto> procedure = procedureService.getProcedureById(id);
        return procedure
                .map(proc -> new ResponseEntity<>(proc, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titre")
    public ResponseEntity<ProcedureDto> getProcedureByTitre(@RequestParam String titre) {
        Optional<ProcedureDto> procedure = procedureService.getProcedureByTitre(titre);
        return procedure
                .map(proc -> new ResponseEntity<>(proc, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/admin-source/{adminSourceId}")
    public ResponseEntity<List<ProcedureDto>> getProceduresByAdminSourceId(@PathVariable Long adminSourceId) {
        List<ProcedureDto> procedures = procedureService.getProceduresByAdminSourceId(adminSourceId);
        return procedures.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : new ResponseEntity<>(procedures, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProcedureDto> saveProcedure(@RequestBody ProcedureDto procedureDto) {
        ProcedureDto createdProcedure = procedureService.saveProcedure(procedureDto);
        return new ResponseEntity<>(createdProcedure, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDto> updateProcedure(@PathVariable("id") Long id,
                                                        @RequestBody ProcedureDto procedureDto) {
        try {
            ProcedureDto updatedProcedure = procedureService.updateProcedure(id, procedureDto);
            return new ResponseEntity<>(updatedProcedure, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable("id") Long id) {
        try {
            procedureService.deleteProcedure(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
