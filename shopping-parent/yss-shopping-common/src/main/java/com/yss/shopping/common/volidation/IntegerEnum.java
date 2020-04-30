package com.yss.shopping.common.volidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 整数枚举
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerEnumValidator.class)
public @interface IntegerEnum {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] intValues();


}