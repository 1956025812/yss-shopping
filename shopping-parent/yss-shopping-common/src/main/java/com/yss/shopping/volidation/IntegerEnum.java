package com.yss.shopping.volidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerEnumValidator.class)
public @interface IntegerEnum {
 
    String message();
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    int[] intValues();
 
 
}