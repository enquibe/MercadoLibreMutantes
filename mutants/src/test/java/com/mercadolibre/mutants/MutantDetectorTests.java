package com.mercadolibre.mutants;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mercadolibre.mutants.detector.MutantDetector;

import org.junit.jupiter.api.Test;

public class MutantDetectorTests {

    private final MutantDetector mutantDetector = new MutantDetector();

    @Test
    void detectsMutant() {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        assertTrue(mutantDetector.isMutant(mutantDna));
    }

    @Test
    void detectsHuman() {
        String[] humanDna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
        assertFalse(mutantDetector.isMutant(humanDna));
    }
}