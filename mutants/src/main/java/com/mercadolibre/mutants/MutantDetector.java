package com.mercadolibre.mutants;

public class MutantDetector {

    private static final int SEQ_LENGTH = 4;

    /**
     * Analiza una secuencia de ADN para determinar si es mutante.
     * 
     * @param dna Secuencia de ADN. Cada elemento del array es una fila de una
     *            matriz cuadrada (NxN). Las letras de los Strings sólo pueden ser:
     *            (A, T, C, G).
     * @return True si encuentra más de una secuencia de {@value #SEQ_LENGTH} letras
     *         iguales, de forma oblicua, horizontal o vertical. False en caso
     *         contrario.
     */
    public boolean isMutant(String[] dna) {
        int count = 0;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                if (findSequenceH(i, j, dna))
                    count++;
                if (findSequenceV(i, j, dna))
                    count++;
                if (findSequenceDP(i, j, dna))
                    count++;
                if (findSequenceDS(i, j, dna))
                    count++;

                if (count >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica en dirección horizontal, partiendo desde [i][j] y avanzando hacia la
     * derecha, si hay una secuencia de {@value #SEQ_LENGTH} letras iguales.
     * 
     * @param i   Índice de la fila
     * @param j   Índice de la columna
     * @param dna Secuencia de ADN.
     * @return True si encuentra una secuencia de {@value #SEQ_LENGTH} letras
     *         iguales. False en caso contrario.
     */
    private boolean findSequenceH(int i, int j, String[] dna) {
        int limit = dna.length - SEQ_LENGTH;
        if (j > limit)
            return false;

        char evaluatedChar = dna[i].charAt(j);

        for (int x = 1; x < SEQ_LENGTH; x++) {
            if (dna[i].charAt(j + x) != evaluatedChar) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica en dirección vertical, partiendo desde [i][j] y avanzando hacia
     * abajo, si hay una secuencia de {@value #SEQ_LENGTH} letras iguales.
     * 
     * @param i   Índice de la fila
     * @param j   Índice de la columna
     * @param dna Secuencia de ADN.
     * @return True si encuentra una secuencia de {@value #SEQ_LENGTH} letras
     *         iguales. False en caso contrario.
     */
    private boolean findSequenceV(int i, int j, String[] dna) {
        int limit = dna.length - SEQ_LENGTH;
        if (i > limit)
            return false;

        char evaluatedChar = dna[i].charAt(j);

        for (int x = 1; x < SEQ_LENGTH; x++) {
            if (dna[i + x].charAt(j) != evaluatedChar) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica en dirección de la diagonal principal, partiendo desde [i][j] y
     * avanzando hacia abajo y hacia la derecha, si hay una secuencia de
     * {@value #SEQ_LENGTH} letras iguales.
     * 
     * @param i   Índice de la fila
     * @param j   Índice de la columna
     * @param dna Secuencia de ADN.
     * @return True si encuentra una secuencia de {@value #SEQ_LENGTH} letras
     *         iguales. False en caso contrario.
     */
    private boolean findSequenceDP(int i, int j, String[] dna) {
        int limit = dna.length - SEQ_LENGTH;
        if (i > limit || j > limit)
            return false;

        char evaluatedChar = dna[i].charAt(j);

        for (int x = 1; x < SEQ_LENGTH; x++) {
            if (dna[i + x].charAt(j + x) != evaluatedChar) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica en dirección de la diagonal secundaria, partiendo desde [i][j] y
     * avanzando hacia abajo y hacia la izquierda, si hay una secuencia de
     * {@value #SEQ_LENGTH} letras iguales.
     * 
     * @param i   Índice de la fila
     * @param j   Índice de la columna
     * @param dna Secuencia de ADN.
     * @return True si encuentra una secuencia de {@value #SEQ_LENGTH} letras
     *         iguales. False en caso contrario.
     */
    private boolean findSequenceDS(int i, int j, String[] dna) {
        int limit = dna.length - SEQ_LENGTH;
        if (i > limit || j < SEQ_LENGTH - 1)
            return false;

        char evaluatedChar = dna[i].charAt(j);

        for (int x = 1; x < SEQ_LENGTH; x++) {
            if (dna[i + x].charAt(j - x) != evaluatedChar) {
                return false;
            }
        }

        return true;
    }

}