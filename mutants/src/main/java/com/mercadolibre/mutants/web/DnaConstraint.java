package com.mercadolibre.mutants.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotEmpty(message = "Requerido")
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = DnaValidator.class)
@Documented
public @interface DnaConstraint {
    String message() default "Invalid dna";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
