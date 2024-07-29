package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.DocumentDto;
import com.mrafp.idarati.Mapper.DocumentMapper;
import com.mrafp.idarati.Model.Document;
import com.mrafp.idarati.Repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {


    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDto> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DocumentDto> getDocumentById(Long id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDto);
    }

    public List<DocumentDto> getDocumentsByTitre(String titre) {
        return documentRepository.findByTitre(titre)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> getDocumentsByCode(String code) {
        return documentRepository.findByCode(code)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> getDocumentsByEstActe(Boolean estActe) {
        return documentRepository.findByEstActe(estActe)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> getDocumentsByDossierId(Long dossierId) {
        return documentRepository.findByDossier_DossierId(dossierId)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> getDocumentsByProcedureId(Long procedureId) {
        return documentRepository.findByProcedure_ProcedureId(procedureId)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DocumentDto> getDocumentsByFamilleDocumentId(Long familleId) {
        return documentRepository.findByFamilleDocument_FamilleId(familleId)
                .stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }


    public DocumentDto updateDocument(Long id, DocumentDto documentDto) {
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

        existingDocument.setCode(documentDto.getCode());
        existingDocument.setTitre(documentDto.getTitre());
        existingDocument.setDescription(documentDto.getDescription());

        Document updatedDocument = documentRepository.save(existingDocument);
        return documentMapper.toDto(updatedDocument);
    }

    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document not found with id: " + id);
        }
        documentRepository.deleteById(id);
    }

}
