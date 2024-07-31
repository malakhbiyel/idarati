package com.mrafp.idarati.Controller;
import com.mrafp.idarati.Dto.CoutDto;
import com.mrafp.idarati.Services.CoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/couts")
public class CoutController {

    private final CoutService coutService;

    public CoutController(CoutService coutService) {
        this.coutService = coutService;
    }

    @GetMapping
    public ResponseEntity<List<CoutDto>> getAllCouts() {
        List<CoutDto> couts = coutService.getAllCouts();
        return new ResponseEntity<>(couts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoutDto> getCoutById(@PathVariable Long id) {
        Optional<CoutDto> coutDto = coutService.getCoutById(id);
        return coutDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/value")
    public ResponseEntity<CoutDto> getCoutByValeur(@RequestParam Double valeur) {
        Optional<CoutDto> coutDto = coutService.getCoutByValeur(valeur);
        return coutDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CoutDto> saveCout(@RequestBody CoutDto coutDto) {
        CoutDto savedCout = coutService.saveCout(coutDto);
        return new ResponseEntity<>(savedCout, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoutDto> updateCout(@PathVariable Long id, @RequestBody CoutDto coutDto) {
        try {
            CoutDto updatedCout = coutService.updateCout(id, coutDto);
            return new ResponseEntity<>(updatedCout, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCout(@PathVariable Long id) {
        try {
            coutService.deleteCout(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
