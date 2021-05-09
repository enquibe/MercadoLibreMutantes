package com.mercadolibre.mutants.stats;

import com.mercadolibre.mutants.db.DnaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private final DnaRepository dnaRepository;

    public StatsController(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @GetMapping() 
    public Stats mutantsRatio() {
        long mutantCount = dnaRepository.countByIsMutant(true);
        long humanCount = dnaRepository.countByIsMutant(false);

        return new Stats(mutantCount, humanCount);
    }
}
