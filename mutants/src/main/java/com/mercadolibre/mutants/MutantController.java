package com.mercadolibre.mutants;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class MutantController {

    private static final Logger logger = LoggerFactory.getLogger(MutantController.class);

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new DetectMutantRequestValidator());
    }

    @PostMapping()
    public ResponseEntity<String> isMutant(@Valid @RequestBody DetectMutantRequest request) {
        return ResponseEntity.ok("valid");
    }

}
