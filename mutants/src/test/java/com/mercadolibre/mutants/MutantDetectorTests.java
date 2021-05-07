package com.mercadolibre.mutants;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MutantDetectorTests {

    private final MutantDetector mutantDetector = new MutantDetector();

    @Test
    void detectsHuman() {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        assertTrue(mutantDetector.isMutant(mutantDna));
    }

    @Test
    void detectsMutant() {
        String[] humanDna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
        assertFalse(mutantDetector.isMutant(humanDna));
    }
}