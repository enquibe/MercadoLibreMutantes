package com.mercadolibre.mutants.detector;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.mercadolibre.mutants.db.Dna;
import com.mercadolibre.mutants.db.DnaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class MutantController {

    private final MutantDetector mutantDetector;

    private final DnaRepository dnaRepository;

    public MutantController(MutantDetector mutantDetector, DnaRepository dnaRepository) {
        this.mutantDetector = mutantDetector;
        this.dnaRepository = dnaRepository;
    }

    @GetMapping()
    public List<Dna> all() {
        return dnaRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> isMutant(@Valid @RequestBody DetectMutantRequest request) {
        boolean isMutant;

        Optional<Dna> existingDna = dnaRepository.findBySequence(request.getDna());
        if (existingDna.isPresent()) {
            isMutant = existingDna.get().isMutant();
        } else {
            isMutant = mutantDetector.isMutant(request.getDna());
            Dna dna = new Dna(request.getDna(), isMutant);
            dnaRepository.save(dna);
        }

        if (isMutant) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
