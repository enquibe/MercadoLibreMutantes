package com.mercadolibre.mutants;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class MutantController {

    private final MutantDetector mutantDetector;

    public MutantController(MutantDetector mutantDetector) {
        this.mutantDetector = mutantDetector;
    }

    @PostMapping()
    public ResponseEntity<?> isMutant(@Valid @RequestBody DetectMutantRequest request) {
        if (mutantDetector.isMutant(request.getDna())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
