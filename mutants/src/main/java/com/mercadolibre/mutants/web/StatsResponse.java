package com.mercadolibre.mutants.web;

public class StatsResponse {
    private long count_mutant_dna;
    private long count_human_dna;
    private double ratio;

    public StatsResponse() {}

    public StatsResponse(long count_mutant_dna, long count_human_dna) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = (double) count_mutant_dna / (count_human_dna != 0 ? count_human_dna : 1);
    }

    public long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public long getCount_human_dna() {
        return count_human_dna;
    }

    public double getRatio() {
        return ratio;
    }
}
