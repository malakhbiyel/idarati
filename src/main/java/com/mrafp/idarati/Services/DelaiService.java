package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.DelaiDto;
import com.mrafp.idarati.Mapper.DelaiMapper;
import com.mrafp.idarati.Model.Delai;
import com.mrafp.idarati.Repository.DelaiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DelaiService {

    private final DelaiRepository delaiRepository;
    private final DelaiMapper delaiMapper;


    public DelaiService(DelaiRepository delaiRepository, DelaiMapper delaiMapper) {
        this.delaiRepository = delaiRepository;
        this.delaiMapper = delaiMapper;
    }

    public List<DelaiDto> getAllDelais() {
        List<Delai> delais = delaiRepository.findAll();
        return delais.stream()
                .map(delaiMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DelaiDto> getDelaiById(Long id) {
        return delaiRepository.findById(id)
                .map(delaiMapper::toDto);
    }

    public Optional<DelaiDto> getDelaiByDuree(Integer duree) {
        return Optional.ofNullable(delaiRepository.findByDuree(duree))
                .map(delaiMapper::toDto);
    }

    public DelaiDto updateDelai(Long id, DelaiDto delaiDto) {
        Delai existingDelai = delaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delai not found with id: " + id));

        existingDelai.setDuree(delaiDto.getDuree());

        Delai updatedDelai = delaiRepository.save(existingDelai);
        return delaiMapper.toDto(updatedDelai);
    }

    public void deleteDelai(Long id) {
        if (!delaiRepository.existsById(id)) {
            throw new RuntimeException("Delai not found with id: " + id);
        }
        delaiRepository.deleteById(id);
    }
}
