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

    public ProcedureDto createProcedure(ProcedureDto procedureDto) {
        Procedure procedure = procedureMapper.toEntity(procedureDto);
        setRelatedEntities(procedure, procedureDto);
        Procedure savedProcedure = procedureRepository.save(procedure);
        return procedureMapper.toDto(savedProcedure);
    }

    public ProcedureDto updateProcedure(Long id, ProcedureDto procedureDto) {
        Procedure existingProcedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));

        existingProcedure.setTitre(procedureDto.getTitre());
        existingProcedure.setDescription(procedureDto.getDescription());
        setRelatedEntities(existingProcedure, procedureDto);

        Procedure updatedProcedure = procedureRepository.save(existingProcedure);
        return procedureMapper.toDto(updatedProcedure);
    }

    public void deleteProcedure(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new RuntimeException("Procedure not found with id: " + id);
        }
        procedureRepository.deleteById(id);
    }

    private void setRelatedEntities(Procedure procedure, ProcedureDto procedureDto) {
        if (procedureDto.getAdminSourceId() != null) {
            Administration adminSource = administrationRepository.findById(procedureDto.getAdminSourceId())
                    .orElseThrow(() -> new RuntimeException("AdminSource not found with id: " + procedureDto.getAdminSourceId()));
            procedure.setAdminSource(adminSource);
        }

        if (procedureDto.getAntenneId() != null) {
            Antenne antenne = antenneRepository.findById(procedureDto.getAntenneId())
                    .orElseThrow(() -> new RuntimeException("Antenne not found with id: " + procedureDto.getAntenneId()));
            procedure.setAntenne(antenne);
        }

        if (procedureDto.getDelaiId() != null) {
            Delai delai = delaiRepository.findById(procedureDto.getDelaiId())
                    .orElseThrow(() -> new RuntimeException("Delai not found with id: " + procedureDto.getDelaiId()));
            procedure.setDelai(delai);
        }

        if (procedureDto.getCoutId() != null) {
            Cout cout = coutRepository.findById(procedureDto.getCoutId())
                    .orElseThrow(() -> new RuntimeException("Cout not found with id: " + procedureDto.getCoutId()));
            procedure.setCout(cout);
        }
    }
}

