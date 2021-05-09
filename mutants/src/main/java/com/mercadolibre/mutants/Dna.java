package com.mercadolibre.mutants;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Dna {
    @Id
    private String id;

    private String[] sequence;

    private boolean isMutant;

    public Dna(String[] sequence, boolean isMutant) {
        this.sequence = sequence;
        this.isMutant = isMutant;
    }

    public String getId() {
        return id;
    }

    public String[] getSequence() {
        return sequence;
    }

    public boolean isMutant() {
        return isMutant;
    }
    
}
