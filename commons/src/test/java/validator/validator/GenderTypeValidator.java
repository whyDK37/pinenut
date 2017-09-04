package validator.validator;

import validator.pojo.GenderType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderTypeValidator implements
        ConstraintValidator<GenderCase, GenderType> {
  GenderType value;

  @Override
  public void initialize(GenderCase constraintAnnotation) {
    value = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(GenderType obj, ConstraintValidatorContext context) {
    if (value != null && obj != null && value != obj) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("gender should be " + value + "| the value is " + obj).addConstraintViolation();
      return false;
    } else {
      return true;
    }
  }
}