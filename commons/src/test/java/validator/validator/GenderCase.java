package validator.validator;

import validator.pojo.GenderType;
import validator.validator.GenderTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderTypeValidator.class)
public @interface GenderCase {
    String message() default "genderType invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    GenderType value() default GenderType.FEMALE;
}