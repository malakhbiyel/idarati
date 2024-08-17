package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.ProcedureDto;
import com.mrafp.idarati.Mapper.ProcedureMapper;
import com.mrafp.idarati.Model.*;
import com.mrafp.idarati.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;
    private final AdministrationRepository administrationRepository;
    private final AntenneRepository antenneRepository;
    private final DelaiRepository delaiRepository;
    private final CoutRepository coutRepository;

    public ProcedureService(ProcedureRepository procedureRepository,
                            ProcedureMapper procedureMapper,
                            AdministrationRepository adminSourceRepository,
                            AntenneRepository antenneRepository,
                            DelaiRepository delaiRepository,
                            CoutRepository coutRepository) {
        this.procedureRepository = procedureRepository;
        this.procedureMapper = procedureMapper;
        this.administrationRepository = adminSourceRepository;
        this.antenneRepository = antenneRepository;
        this.delaiRepository = delaiRepository;
        this.coutRepository = coutRepository;
    }

    public List<ProcedureDto> getAllProcedures() {
        return procedureRepository.findAll()
                .stream()
                .map(procedureMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ProcedureDto> getProcedureById(Long id) {
        return procedureRepository.findById(id)
                .map(procedureMapper::toDto);
    }
    public Optional<ProcedureDto> getProcedureByTitre(String titre) {
        return procedureRepository.findByTitre(titre)
                .map(procedureMapper::toDto);
    }

    public List<ProcedureDto> getProceduresByAdminSourceId(Long adminSourceId) {
        return procedureRepository.findByAdministration_AdministrationId(adminSourceId)
                .stream()
                .map(procedureMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProcedureDto saveProcedure(ProcedureDto procedureDto) {
        Procedure procedure = procedureMapper.toEntity(procedureDto);

        procedure.setAdministration(administrationRepository.findById(procedureDto.getAdministration().getAdministrationId())
                .orElseThrow(() -> new RuntimeException("Administration not found with id: " + procedureDto.getAdministration().getAdministrationId())));

        procedure.setAntenne(antenneRepository.findById(procedureDto.getAntenne().getAntenneId())
                .orElseThrow(() -> new RuntimeException("Antenne not found with id: " + procedureDto.getAntenne().getAntenneId())));

        procedure.setDelai(delaiRepository.findById(procedureDto.getDelai().getDelaiId())
                .orElseThrow(() -> new RuntimeException("Delai not found with id: " + procedureDto.getDelai().getDelaiId())));

        procedure.setCout(coutRepository.findById(procedureDto.getCout().getCoutId())
                .orElseThrow(() -> new RuntimeException("Cout not found with id: " + procedureDto.getCout().getCoutId())));

        Procedure savedProcedure = procedureRepository.save(procedure);
        return procedureMapper.toDto(savedProcedure);
    }

    public ProcedureDto updateProcedure(Long id, ProcedureDto procedureDto) {
        Procedure existingProcedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));

        existingProcedure.setTitre(procedureDto.getTitre());
        existingProcedure.setDescription(procedureDto.getDescription());
        existingProcedure.setAdministration(administrationRepository.findById(procedureDto.getAdministration().getAdministrationId())
                .orElseThrow(() -> new RuntimeException("Administaration not found with id: " + procedureDto.getAdministration().getAdministrationId())));
        existingProcedure.setAntenne(antenneRepository.findById(procedureDto.getAntenne().getAntenneId())
                .orElseThrow(() -> new RuntimeException("Antenne not found with id: " + procedureDto.getAntenne().getAntenneId())));
        existingProcedure.setDelai(delaiRepository.findById(procedureDto.getDelai().getDelaiId())
                .orElseThrow(() -> new RuntimeException("Delai not found with id: " + procedureDto.getDelai().getDelaiId())));
        existingProcedure.setCout(coutRepository.findById(procedureDto.getCout().getCoutId())
                .orElseThrow(() -> new RuntimeException("Cout not found with id: " + procedureDto.getCout().getCoutId())));

        Procedure updatedProcedure = procedureRepository.save(existingProcedure);
        return procedureMapper.toDto(updatedProcedure);
    }

    public void deleteProcedure(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new RuntimeException("Procedure not found with id: " + id);
        }
        procedureRepository.deleteById(id);
    }

}

