package com.mrafp.idarati.Services;

import com.mrafp.idarati.Dto.ConditionDto;
import com.mrafp.idarati.Mapper.ConditionMapper;
import com.mrafp.idarati.Model.Condition;
import com.mrafp.idarati.Repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;
    private final ConditionMapper conditionMapper;

    public ConditionService(ConditionRepository conditionRepository, ConditionMapper conditionMapper) {
        this.conditionRepository = conditionRepository;
        this.conditionMapper = conditionMapper;
    }

    public List<ConditionDto> getAllConditions() {
        List<Condition> conditions = conditionRepository.findAll();
        return conditions.stream()
                .map(conditionMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ConditionDto> getConditionById(Long id) {
        return conditionRepository.findById(id)
                .map(conditionMapper::toDto);
    }

    public Optional<ConditionDto> getConditionByNomCondition(String nomCondition) {
        return conditionRepository.findByNomCondition(nomCondition)
                .map(conditionMapper::toDto);
    }

    public ConditionDto updateCondition(Long id, ConditionDto conditionDto) {
        Condition existingCondition = conditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condition not found with id: " + id));

        existingCondition.setNomCondition(conditionDto.getNomCondition());
        existingCondition.setTerme(conditionDto.getTerme());

        Condition updatedCondition = conditionRepository.save(existingCondition);
        return conditionMapper.toDto(updatedCondition);
    }

    public void deleteCondition(Long id) {
        if (!conditionRepository.existsById(id)) {
            throw new RuntimeException("Condition not found with id: " + id);
        }
        conditionRepository.deleteById(id);
    }
}
