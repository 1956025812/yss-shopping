package com.yss.shopping.volidation;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
 
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Map;
 
public class IntegerEnumValidator implements ConstraintValidator<IntegerEnum, Object> {
 
    private final String intVaules = "intValues";
 
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(null == value) {
            return true;
        }
        ConstraintDescriptor<?> constraintDescriptor = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor();
        Map<String, Object> attributes = constraintDescriptor.getAttributes();
        int[] intValues = (int[]) attributes.get(intVaules);
        boolean hasFlag = false;
        for (int intValue : intValues) {
            if (intValue == ((int) value)) {
                hasFlag = true;
                break;
            }
        }
        return hasFlag;
    }
}