package com.mercadolibre.mutants.model;

import java.util.Arrays;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (isMutant ? 1231 : 1237);
        result = prime * result + Arrays.hashCode(sequence);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dna other = (Dna) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isMutant != other.isMutant)
            return false;
        if (!Arrays.equals(sequence, other.sequence))
            return false;
        return true;
    }    
    
}
