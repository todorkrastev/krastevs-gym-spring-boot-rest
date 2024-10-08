package com.todorkrastev.krastevsgymrest.validation.annotation;

import com.todorkrastev.krastevsgymrest.validation.validator.UniqueTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTitleValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTitle {
    String message() default "Title already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
