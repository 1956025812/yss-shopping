package com.yss.shopping.common.volidation;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Map;

/**
 * <p>
 * 整数枚举校验类
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public class IntegerEnumValidator implements ConstraintValidator<IntegerEnum, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        ConstraintDescriptor<?> constraintDescriptor = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor();
        Map<String, Object> attributes = constraintDescriptor.getAttributes();
        String intValues = "intValues";
        int[] intValuesArray = (int[]) attributes.get(intValues);
        boolean hasFlag = false;
        for (int intValue : intValuesArray) {
            if (intValue == ((int) value)) {
                hasFlag = true;
                break;
            }
        }
        return hasFlag;
    }
}