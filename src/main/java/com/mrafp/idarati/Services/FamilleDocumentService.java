package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.FamilleDocumentDto;
import com.mrafp.idarati.Mapper.DocumentMapper;
import com.mrafp.idarati.Mapper.FamilleDocumentMapper;
import com.mrafp.idarati.Model.FamilleDocument;
import com.mrafp.idarati.Repository.FamilleDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamilleDocumentService {

    private final FamilleDocumentRepository familleDocumentRepository;
    private final FamilleDocumentMapper familleDocumentMapper;
    private final DocumentMapper documentMapper;

    public FamilleDocumentService(FamilleDocumentRepository familleDocumentRepository,
                                  FamilleDocumentMapper familleDocumentMapper,
                                  DocumentMapper documentMapper) {
        this.familleDocumentRepository = familleDocumentRepository;
        this.familleDocumentMapper = familleDocumentMapper;
        this.documentMapper = documentMapper;
    }

    public List<FamilleDocumentDto> getAllFamilleDocuments() {
        return familleDocumentRepository.findAll()
                .stream()
                .map(familleDocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<FamilleDocumentDto> getFamilleDocumentById(Long id) {
        return familleDocumentRepository.findById(id)
                .map(familleDocumentMapper::toDto);
    }

    public FamilleDocumentDto updateFamilleDocument(Long id, FamilleDocumentDto familleDocumentDto) {
        FamilleDocument existingFamilleDocument = familleDocumentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FamilleDocument not found with id: " + id));

        existingFamilleDocument.setDocumentsList(familleDocumentDto.getDocumentsList()
                .stream()
                .map(documentMapper::toEntity)
                .collect(Collectors.toList()));

        FamilleDocument updatedFamilleDocument = familleDocumentRepository.save(existingFamilleDocument);
        return familleDocumentMapper.toDto(updatedFamilleDocument);
    }

    public void deleteFamilleDocument(Long id) {
        if (!familleDocumentRepository.existsById(id)) {
            throw new RuntimeException("FamilleDocument not found with id: " + id);
        }
        familleDocumentRepository.deleteById(id);
    }
}
