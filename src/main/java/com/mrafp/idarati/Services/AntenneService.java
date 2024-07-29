package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.AntenneDto;
import com.mrafp.idarati.Mapper.AntenneMapper;
import com.mrafp.idarati.Model.Antenne;
import com.mrafp.idarati.Repository.AntenneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AntenneService {

    private final AntenneRepository antenneRepository;
    private final AntenneMapper antenneMapper;


    public AntenneService(AntenneRepository antenneRepository, AntenneMapper antenneMapper) {
        this.antenneRepository = antenneRepository;
        this.antenneMapper = antenneMapper;
    }


    public AntenneDto save(AntenneDto antenneDto) {
        Antenne antenne = antenneMapper.toEntity(antenneDto);
        Antenne savedAntenne = antenneRepository.save(antenne);
        return antenneMapper.toDto(savedAntenne);
    }


    public Optional<AntenneDto> findById(Long id) {
        return antenneRepository.findById(id)
                .map(antenneMapper::toDto);
    }


    public List<AntenneDto> getAllAntennes() {
        return antenneRepository.findAll()
                .stream()
                .map(antenneMapper::toDto)
                .collect(Collectors.toList());
    }


    public Optional<AntenneDto> getAntenneByAdminDepot(String adminDepot) {
        return antenneRepository.findByAdminDepot(adminDepot)
                .map(antenneMapper::toDto);
    }


    public Optional<AntenneDto> getAntenneByAdminReception(String adminReception) {
        return antenneRepository.findByAdminReception(adminReception)
                .map(antenneMapper::toDto);
    }

    public AntenneDto updateAntenne(Long id, AntenneDto antenneDto) {
        Antenne existingAntenne = antenneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Antenne not found with id: " + id));

        existingAntenne.setAdminDepot(antenneDto.getAdminDepot());
        existingAntenne.setAdminReception(antenneDto.getAdminReception());

        Antenne updatedAntenne = antenneRepository.save(existingAntenne);
        return antenneMapper.toDto(updatedAntenne);
    }


    public void deleteAntenne(Long id) {
        if (!antenneRepository.existsById(id)) {
            throw new RuntimeException("Antenne not found with id: " + id);
        }
        antenneRepository.deleteById(id);
    }
}
