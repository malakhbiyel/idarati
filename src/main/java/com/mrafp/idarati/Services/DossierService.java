package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.DossierDto;
import com.mrafp.idarati.Mapper.DocumentMapper;
import com.mrafp.idarati.Mapper.DossierMapper;
import com.mrafp.idarati.Model.Dossier;
import com.mrafp.idarati.Repository.ConditionRepository;
import com.mrafp.idarati.Repository.DossierRepository;
import com.mrafp.idarati.Repository.ProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DossierService {

    private final DossierRepository dossierRepository;
    private final DossierMapper dossierMapper;
    private final DocumentMapper documentMapper;
    private final ProcedureRepository procedureRepository;
    private final ConditionRepository conditionRepository;

    public DossierService(DossierRepository dossierRepository, DossierMapper dossierMapper, DocumentMapper documentMapper,
                          ProcedureRepository procedureRepository, ConditionRepository conditionRepository) {
        this.dossierRepository = dossierRepository;
        this.dossierMapper = dossierMapper;
        this.documentMapper = documentMapper;
        this.procedureRepository = procedureRepository;
        this.conditionRepository = conditionRepository;
    }

    public List<DossierDto> getAllDossiers() {
        return dossierRepository.findAll()
                .stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DossierDto> getDossierById(Long id) {
        return dossierRepository.findById(id)
                .map(dossierMapper::toDto);
    }

    public List<DossierDto> getDossiersByProcedureId(Long procedureId) {
        return dossierRepository.findByProcedure_ProcedureId(procedureId)
                .stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DossierDto> getDossiersByConditionId(Long conditionId) {
        return dossierRepository.findByCondition_ConditionId(conditionId)
                .stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DossierDto> getDossiersByDocumentId(Long documentId) {
        return dossierRepository.findByDocumentsList_DocumentId(documentId)
                .stream()
                .map(dossierMapper::toDto)
                .collect(Collectors.toList());
    }

    public DossierDto saveDossier(DossierDto dossierDto) {
        Dossier dossier = dossierMapper.toEntity(dossierDto);

        dossier.setProcedure(procedureRepository.findById(dossierDto.getProcedure().getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dossierDto.getProcedure().getProcedureId())));

        dossier.setCondition(conditionRepository.findById(dossierDto.getCondition().getConditionId())
                .orElseThrow(() -> new RuntimeException("Condition not found with id: " + dossierDto.getCondition().getConditionId())));

        dossier.setDocumentsList(dossierDto.getDocumentsList()
                .stream()
                .map(documentMapper::toEntity)
                .collect(Collectors.toList()));

        Dossier savedDossier = dossierRepository.save(dossier);
        return dossierMapper.toDto(savedDossier);
    }

    public DossierDto updateDossier(Long id, DossierDto dossierDto) {
        Dossier existingDossier = dossierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier not found with id: " + id));

        existingDossier.setProcedure(procedureRepository.findById(dossierDto.getProcedure().getProcedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dossierDto.getProcedure().getProcedureId())));

        existingDossier.setCondition(conditionRepository.findById(dossierDto.getCondition().getConditionId())
                .orElseThrow(() -> new RuntimeException("Condition not found with id: " + dossierDto.getCondition().getConditionId())));

        existingDossier.setDocumentsList(dossierDto.getDocumentsList()
                .stream()
                .map(documentMapper::toEntity)
                .collect(Collectors.toList()));

        Dossier updatedDossier = dossierRepository.save(existingDossier);
        return dossierMapper.toDto(updatedDossier);
    }

    public void deleteDossier(Long id) {
        if (!dossierRepository.existsById(id)) {
            throw new RuntimeException("Dossier not found with id: " + id);
        }
        dossierRepository.deleteById(id);
    }
}
