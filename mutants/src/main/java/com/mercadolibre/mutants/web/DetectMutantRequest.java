package com.mercadolibre.mutants.web;

public class DetectMutantRequest {

    public DetectMutantRequest() {

    }

    public DetectMutantRequest(String[] dna) {
        this.dna = dna;
    }

    @DnaConstraint
    private String[] dna;

    public String[] getDna() {
        return dna;
    }
}
