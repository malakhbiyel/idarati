package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Dto.ConditionDto;
import com.mrafp.idarati.Services.ConditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

    private final ConditionService conditionService;

    public ConditionController(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @GetMapping
    public ResponseEntity<List<ConditionDto>> getAllConditions() {
        List<ConditionDto> conditions = conditionService.getAllConditions();
        return new ResponseEntity<>(conditions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionDto> getConditionById(@PathVariable Long id) {
        Optional<ConditionDto> conditionDto = conditionService.getConditionById(id);
        return conditionDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nom/{nomCondition}")
    public ResponseEntity<ConditionDto> getConditionByNomCondition(@PathVariable String nomCondition) {
        Optional<ConditionDto> conditionDto = conditionService.getConditionByNomCondition(nomCondition);
        return conditionDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ConditionDto> saveCondition(@RequestBody ConditionDto conditionDto) {
        try {
            ConditionDto savedCondition = conditionService.saveCondition(conditionDto);
            return new ResponseEntity<>(savedCondition, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConditionDto> updateCondition(@PathVariable Long id, @RequestBody ConditionDto conditionDto) {
        try {
            ConditionDto updatedCondition = conditionService.updateCondition(id, conditionDto);
            return new ResponseEntity<>(updatedCondition, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        try {
            conditionService.deleteCondition(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

