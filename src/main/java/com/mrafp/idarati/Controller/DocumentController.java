package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Services.DocumentService;
import com.mrafp.idarati.Dto.DocumentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        List<DocumentDto> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        Optional<DocumentDto> documentDto = documentService.getDocumentById(id);
        return documentDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/title")
    public ResponseEntity<List<DocumentDto>> getDocumentsByTitre(@RequestParam String titre) {
        List<DocumentDto> documents = documentService.getDocumentsByTitre(titre);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/code")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCode(@RequestParam String code) {
        List<DocumentDto> documents = documentService.getDocumentsByCode(code);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/isActe")
    public ResponseEntity<List<DocumentDto>> getDocumentsByEstActe(@RequestParam Boolean estActe) {
        List<DocumentDto> documents = documentService.getDocumentsByEstActe(estActe);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/dossier")
    public ResponseEntity<List<DocumentDto>> getDocumentsByDossierId(@RequestParam Long dossierId) {
        List<DocumentDto> documents = documentService.getDocumentsByDossierId(dossierId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/procedure")
    public ResponseEntity<List<DocumentDto>> getDocumentsByProcedureId(@RequestParam Long procedureId) {
        List<DocumentDto> documents = documentService.getDocumentsByProcedureId(procedureId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/famille")
    public ResponseEntity<List<DocumentDto>> getDocumentsByFamilleDocumentId(@RequestParam Long familleId) {
        List<DocumentDto> documents = documentService.getDocumentsByFamilleDocumentId(familleId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentDto> saveDocument(@RequestBody DocumentDto documentDto) {
        DocumentDto savedDocument = documentService.saveDocument(documentDto);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable Long id, @RequestBody DocumentDto documentDto) {
        try {
            DocumentDto updatedDocument = documentService.updateDocument(id, documentDto);
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        try {
            documentService.deleteDocument(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}