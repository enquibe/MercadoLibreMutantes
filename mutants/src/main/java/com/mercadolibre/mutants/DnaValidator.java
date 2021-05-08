package com.mercadolibre.mutants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<DnaConstraint, String[]> {

    List<Character> allowed = Arrays.asList('A', 'T', 'C', 'G');

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        
        if (value == null) {
            return true;
        }

        Set<String> errors = new HashSet<>();
        
        for (String row : value) {
            if (row.length() != value.length) {
                errors.add("La matriz debe ser cuadrada (NxN)");
            }
            
            for (int i = 0; i < row.length(); i++) {
                if (!allowed.contains(row.charAt(i))) {
                    errors.add(String.format("Sólo se admiten los siguientes caracteres: %s", allowed));
                }
            }
        }

        if (errors.isEmpty()) {
            return true;
        }

        setConstraintViolation(context, errors);
        return false;
    }

    private void setConstraintViolation(ConstraintValidatorContext context, Set<String> errors) {
        context.disableDefaultConstraintViolation();
        errors.forEach(x -> context.buildConstraintViolationWithTemplate(x).addConstraintViolation());
    }
    
}
