package com.mercadolibre.mutants;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<DnaConstraint, String[]> {

    List<Character> allowed = Arrays.asList('A', 'T', 'C', 'G');

    @Override
    public boolean isValid(String[] value, ConstraintValidatorContext context) {
        
        if (value == null) {
            return true;
        }

        for (String row : value) {
            if (row.length() != value.length) {
                setConstraintViolation(context, "La matriz debe ser cuadrada (NxN)");
                return false;
            }
            
            for (int i = 0; i < row.length(); i++) {
                if (!allowed.contains(row.charAt(i))) {
                    setConstraintViolation(context, String.format("Sólo se admiten los siguientes caracteres: %s", allowed));
                    return false;
                }
            }
        }

        return true;
    }

    private void setConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
    
}
