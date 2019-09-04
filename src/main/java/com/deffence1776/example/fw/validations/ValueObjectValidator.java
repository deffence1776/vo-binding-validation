package com.deffence1776.example.fw.validations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValueObjectValidator implements ConstraintValidator<ValueObject, Object> {

     private static Validator validator;

    public ValueObjectValidator(@Qualifier("mvcValidator") Validator validator) {
        ValueObjectValidator.validator=validator;
    }

    @Override
    public void initialize(ValueObject constraintAnnotation) { }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(null==value) {
            return true;
        }
        context.disableDefaultConstraintViolation();

        Errors errors = new BeanPropertyBindingResult(value, "objectName");
        validator.validate(value, errors);

        if (errors.hasErrors()) {
            errors.getFieldErrors().stream().forEach(fe ->
                    context.buildConstraintViolationWithTemplate(fe.getDefaultMessage())
                            .addConstraintViolation()
            );
            return false;
        }
        return true;
    }
}
