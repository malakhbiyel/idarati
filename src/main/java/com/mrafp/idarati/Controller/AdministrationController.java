package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Dto.AdministrationDto;
import com.mrafp.idarati.Services.AdministrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrations")
public class AdministrationController {

    private final AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrationDto> getAdministrationById(@PathVariable Long id) {
        try {
            AdministrationDto administrationDto = administrationService.getAdministrationById(id);
            return new ResponseEntity<>(administrationDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AdministrationDto>> getAllAdministrations() {
        List<AdministrationDto> administrations = administrationService.getAllAdministrations();
        return new ResponseEntity<>(administrations, HttpStatus.OK);
    }

    @GetMapping("/name/{nomAdmin}")
    public ResponseEntity<AdministrationDto> getAdministrationByNomAdmin(@PathVariable String nomAdmin) {
        try {
            AdministrationDto administrationDto = administrationService.getAdministrationByNomAdmin(nomAdmin);
            return new ResponseEntity<>(administrationDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AdministrationDto> saveAdministration(@RequestBody AdministrationDto administrationDto) {
        AdministrationDto savedAdministration = administrationService.saveAdministration(administrationDto);
        return new ResponseEntity<>(savedAdministration, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrationDto> updateAdministration(
            @PathVariable Long id,
            @RequestBody AdministrationDto administrationDto) {
        try {
            AdministrationDto updatedAdministration = administrationService.updateAdministration(id, administrationDto);
            return new ResponseEntity<>(updatedAdministration, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministration(@PathVariable Long id) {
        try {
            administrationService.deleteAdministration(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
