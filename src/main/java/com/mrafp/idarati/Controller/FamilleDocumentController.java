package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Services.FamilleDocumentService;
import com.mrafp.idarati.Dto.FamilleDocumentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/famille-documents")
public class FamilleDocumentController {

    private final FamilleDocumentService familleDocumentService;

    public FamilleDocumentController(FamilleDocumentService familleDocumentService) {
        this.familleDocumentService = familleDocumentService;
    }

    @GetMapping
    public ResponseEntity<List<FamilleDocumentDto>> getAllFamilleDocuments() {
        List<FamilleDocumentDto> familleDocuments = familleDocumentService.getAllFamilleDocuments();
        return new ResponseEntity<>(familleDocuments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamilleDocumentDto> getFamilleDocumentById(@PathVariable("id") Long id) {
        Optional<FamilleDocumentDto> familleDocument = familleDocumentService.getFamilleDocumentById(id);
        return familleDocument
                .map(familleDoc -> new ResponseEntity<>(familleDoc, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<FamilleDocumentDto> saveFamilleDocument(@RequestBody FamilleDocumentDto familleDocumentDto) {
        FamilleDocumentDto savedFamilleDocument = familleDocumentService.saveFamilleDocument(familleDocumentDto);
        return new ResponseEntity<>(savedFamilleDocument, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<FamilleDocumentDto> updateFamilleDocument(@PathVariable("id") Long id,
                                                                    @RequestBody FamilleDocumentDto familleDocumentDto) {
        try {
            FamilleDocumentDto updatedFamilleDocument = familleDocumentService.updateFamilleDocument(id, familleDocumentDto);
            return new ResponseEntity<>(updatedFamilleDocument, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFamilleDocument(@PathVariable("id") Long id) {
        try {
            familleDocumentService.deleteFamilleDocument(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}