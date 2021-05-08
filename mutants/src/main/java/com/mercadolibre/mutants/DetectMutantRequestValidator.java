package com.mercadolibre.mutants;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DetectMutantRequestValidator implements Validator {

    private final List<Character> allowed = Arrays.asList('A', 'T', 'C', 'G');

    @Override
    public boolean supports(Class<?> clazz) {
        return DetectMutantRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        DetectMutantRequest m = (DetectMutantRequest) obj;
        String[] dna = m.getDna();

        for (String row : dna) {
            if (row.length() != dna.length) {
                e.rejectValue("dna", "La matriz debe ser cuadrada (NxN)");
            }
            
            for (int i = 0; i < row.length(); i++) {
                char cellValue = row.charAt(i);
                if (!allowed.contains(cellValue)) {
                    e.rejectValue("dna", String.format(
                        "Caracter inválido: %s. Sólo se admiten los siguientes caracteres: %s", cellValue, allowed
                    ));
                }
            }
        }
    }

}