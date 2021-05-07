package com.mercadolibre.mutants;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @Disabled
    void throwsIfInvalidInput_Letters() {
        String[] invalidDna = { "ATGCGA", "CAGTGC", "TKTATTT", "AGACGG", "GCGTCA", "TCACTG" };
        Exception exception = assertThrows(Exception.class, () -> mutantDetector.isMutant(invalidDna));

    }

    @Test
    @Disabled
    void throwsIfInvalidInput_MatrixSize() {
        String[] invalidDna = { "ATGCGA", "CAGTGC", "TKTATTT", "AGACGG", "GCGTCA", "TCACTG" };
        Exception exception = assertThrows(Exception.class, () -> mutantDetector.isMutant(invalidDna));

    }
}