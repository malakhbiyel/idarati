package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.AdministrationDto;
import com.mrafp.idarati.Mapper.AdministrationMapper;
import com.mrafp.idarati.Model.Administration;
import com.mrafp.idarati.Repository.AdministrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrationService {

    private final AdministrationRepository administrationRepository;
    private final AdministrationMapper administrationMapper;

    public AdministrationService(AdministrationRepository administrationRepository, AdministrationMapper administrationMapper) {
        this.administrationRepository = administrationRepository;
        this.administrationMapper = administrationMapper;
    }

    public AdministrationDto getAdministrationById(Long id) {
        Administration administration = administrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administration not found"));
        return administrationMapper.toDto(administration);
    }

    public List<AdministrationDto> getAllAdministrations() {
        return administrationRepository.findAll().stream()
                .map(administrationMapper::toDto)
                .toList();
    }

    public AdministrationDto getAdministrationByNomAdmin(String nomAdmin) {
        Administration administration = administrationRepository.findByNomAdmin(nomAdmin);
        if (administration == null) {
            throw new RuntimeException("Administration not found with name: " + nomAdmin);
        }
        return administrationMapper.toDto(administration);
    }

    public AdministrationDto saveAdministration(AdministrationDto administrationDto) {
        Administration administration = administrationMapper.toEntity(administrationDto);
        administration = administrationRepository.save(administration);
        return administrationMapper.toDto(administration);
    }

    public AdministrationDto updateAdministration(Long id, AdministrationDto administrationDto) {
        Administration existingAdministration = administrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administration not found with id: " + id));

        existingAdministration.setNomAdmin(administrationDto.getNomAdmin());

        existingAdministration = administrationRepository.save(existingAdministration);
        return administrationMapper.toDto(existingAdministration);
    }

    public void deleteAdministration(Long id) {
        if (!administrationRepository.existsById(id)) {
        throw new RuntimeException("Administration not found with id: " + id);
    }
        administrationRepository.deleteById(id);
    }
}
