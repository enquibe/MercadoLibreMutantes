package com.mercadolibre.mutants;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DnaRepository extends MongoRepository<Dna, String> {
    Optional<Dna> findBySequence(String[] sequence);
}
