package com.mrafp.idarati.Controller;

import com.mrafp.idarati.Dto.DelaiDto;
import com.mrafp.idarati.Services.DelaiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/delais")
public class DelaiController {

        private final DelaiService delaiService;

        public DelaiController(DelaiService delaiService) {
            this.delaiService = delaiService;
        }

        @GetMapping
        public ResponseEntity<List<DelaiDto>> getAllDelais() {
            List<DelaiDto> delais = delaiService.getAllDelais();
            return new ResponseEntity<>(delais, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<DelaiDto> getDelaiById(@PathVariable Long id) {
            Optional<DelaiDto> delaiDto = delaiService.getDelaiById(id);
            return delaiDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/duration")
        public ResponseEntity<DelaiDto> getDelaiByDuree(@RequestParam Integer duree) {
            Optional<DelaiDto> delaiDto = delaiService.getDelaiByDuree(duree);
            return delaiDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping
        public ResponseEntity<DelaiDto> saveDelai(@RequestBody DelaiDto delaiDto) {
            DelaiDto savedDelai = delaiService.saveDelai(delaiDto);
            return new ResponseEntity<>(savedDelai, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<DelaiDto> updateDelai(@PathVariable Long id, @RequestBody DelaiDto delaiDto) {
            try {
                DelaiDto updatedDelai = delaiService.updateDelai(id, delaiDto);
                return new ResponseEntity<>(updatedDelai, HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDelai(@PathVariable Long id) {
            try {
                delaiService.deleteDelai(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
}
