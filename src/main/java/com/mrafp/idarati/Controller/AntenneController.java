package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Dto.AntenneDto;
import com.mrafp.idarati.Services.AntenneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/antennes")
public class AntenneController {

    private final AntenneService antenneService;

    public AntenneController(AntenneService antenneService) {
        this.antenneService = antenneService;
    }

    @PostMapping
    public ResponseEntity<AntenneDto> saveAntenne(@RequestBody AntenneDto antenneDto) {
        AntenneDto savedAntenne = antenneService.save(antenneDto);
        return new ResponseEntity<>(savedAntenne, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AntenneDto> getAntenneById(@PathVariable Long id) {
        Optional<AntenneDto> antenneDto = antenneService.findById(id);
        return antenneDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<AntenneDto>> getAllAntennes() {
        List<AntenneDto> antennes = antenneService.getAllAntennes();
        return new ResponseEntity<>(antennes, HttpStatus.OK);
    }

    @GetMapping("/admin-depot/{adminDepot}")
    public ResponseEntity<AntenneDto> getAntenneByAdminDepot(@PathVariable String adminDepot) {
        Optional<AntenneDto> antenneDto = antenneService.getAntenneByAdminDepot(adminDepot);
        return antenneDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/admin-reception/{adminReception}")
    public ResponseEntity<AntenneDto> getAntenneByAdminReception(@PathVariable String adminReception) {
        Optional<AntenneDto> antenneDto = antenneService.getAntenneByAdminReception(adminReception);
        return antenneDto
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AntenneDto> updateAntenne(@PathVariable Long id, @RequestBody AntenneDto antenneDto) {
        try {
            AntenneDto updatedAntenne = antenneService.updateAntenne(id, antenneDto);
            return new ResponseEntity<>(updatedAntenne, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAntenne(@PathVariable Long id) {
        try {
            antenneService.deleteAntenne(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
