package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.CoutDto;
import com.mrafp.idarati.Mapper.CoutMapper;
import com.mrafp.idarati.Model.Cout;
import com.mrafp.idarati.Repository.CoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoutService {
    private final CoutRepository coutRepository;
    private final CoutMapper coutMapper;


    public CoutService(CoutRepository coutRepository, CoutMapper coutMapper) {
        this.coutRepository = coutRepository;
        this.coutMapper = coutMapper;
    }


    public List<CoutDto> getAllCouts() {
        List<Cout> couts = coutRepository.findAll();
        return couts.stream()
                .map(coutMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CoutDto> getCoutById(Long id) {
        return coutRepository.findById(id)
                .map(coutMapper::toDto);
    }

    public Optional<CoutDto> getCoutByValeur(Double valeur) {
        return Optional.ofNullable(coutRepository.findByValeur(valeur))
                .map(coutMapper::toDto);
    }

    public CoutDto updateCout(Long id, CoutDto coutDto) {
        Cout existingCout = coutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cout not found with id: " + id));

        existingCout.setValeur(coutDto.getValeur());

        Cout updatedCout = coutRepository.save(existingCout);
        return coutMapper.toDto(updatedCout);
    }

    public void deleteCout(Long id) {
        if (!coutRepository.existsById(id)) {
            throw new RuntimeException("Cout not found with id: " + id);
        }
        coutRepository.deleteById(id);
    }
}
