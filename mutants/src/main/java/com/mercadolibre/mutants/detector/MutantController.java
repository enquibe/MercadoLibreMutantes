package com.mercadolibre.mutants.detector;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
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

    private final MutantService mutantService;

    @Value("${spring.data.mongodb.uri:1234}")
    private String mongodbUri;

    public MutantController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @GetMapping()
    public String getMongoUri() {
        return mongodbUri;
    }

    @PostMapping()
    public ResponseEntity<?> isMutant(@Valid @RequestBody DetectMutantRequest request) {
        boolean isMutant = mutantService.isMutant(request.getDna());

        if (isMutant) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
