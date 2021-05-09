package com.mercadolibre.mutants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import com.mercadolibre.mutants.model.Dna;
import com.mercadolibre.mutants.repository.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class DnaRepositoryTests {
    
    @Autowired
    private DnaRepository repository;

    @BeforeEach
	void clean() {
		repository.deleteAll();
	}

    @Test
    void injectedComponentIsNotNull(){
        assertNotNull(repository);
    }

    @Test
	void setsIdOnSave() {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		var savedDna = repository.save(new Dna(mutantDna, true));
        assertNotNull(savedDna.getId());
	}

    @Test
    void findsDnaBySequence() {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        var savedDna = repository.save(new Dna(mutantDna, true));

        Optional<Dna> foundDna = repository.findBySequence(mutantDna);

        assertTrue(foundDna.isPresent());
        assertEquals(savedDna, foundDna.get());
    }

    @Test
    void countsByIsMutant() {
        String[] mutantDna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        repository.save(new Dna(mutantDna, true));

        String[] humanDna = { "ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        repository.save(new Dna(humanDna, false));

        String[] humanDna2 = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        repository.save(new Dna(humanDna2, false));

        long mutantCount = repository.countByIsMutant(true);
        long humanCount = repository.countByIsMutant(false);

        assertEquals(mutantCount, 1);
        assertEquals(humanCount, 2);
    }
}