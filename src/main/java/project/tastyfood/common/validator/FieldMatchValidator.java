package project.tastyfood.common.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;
    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName=constraintAnnotation.first();
        this.secondFieldName=constraintAnnotation.second();
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper wrapper= PropertyAccessorFactory.forBeanPropertyAccess(o);
        Object firstValue=wrapper.getPropertyValue(firstFieldName);
        Object secondValue=wrapper.getPropertyValue(secondFieldName);
        boolean valid= (firstValue==null &&secondValue==null)||(firstValue!=null&&firstValue.equals(secondValue));
        if(!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName)
                    .addConstraintViolation().buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondFieldName).addConstraintViolation().disableDefaultConstraintViolation();
        }
        return valid;
    }
}
