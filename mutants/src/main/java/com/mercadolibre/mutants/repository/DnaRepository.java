package com.mercadolibre.mutants.repository;

import java.util.Optional;

import com.mercadolibre.mutants.model.Dna;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DnaRepository extends MongoRepository<Dna, String> {
    Optional<Dna> findBySequence(String[] sequence);
    long countByIsMutant(boolean isMutant);
}
