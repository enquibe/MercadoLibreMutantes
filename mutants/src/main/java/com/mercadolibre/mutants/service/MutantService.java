package com.mercadolibre.mutants.service;

import java.util.Optional;

import com.mercadolibre.mutants.model.Dna;
import com.mercadolibre.mutants.repository.DnaRepository;

import org.springframework.stereotype.Service;

@Service
public class MutantService {
    private final DnaRepository dnaRepository;
    private final MutantDetector mutantDetector;

    public MutantService(DnaRepository dnaRepository, MutantDetector mutantDetector) {
        this.dnaRepository = dnaRepository;
        this.mutantDetector = mutantDetector;
    }

    public boolean isMutant(String[] dnaSequence) {
        boolean isMutant;

        Optional<Dna> existingDna = dnaRepository.findBySequence(dnaSequence);
        if (existingDna.isPresent()) {
            isMutant = existingDna.get().isMutant();
        } else {
            isMutant = mutantDetector.isMutant(dnaSequence);
            Dna dna = new Dna(dnaSequence, isMutant);
            dnaRepository.save(dna);
        }

        return isMutant;
    }
}
