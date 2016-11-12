package validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.testng.annotations.Test;
import validator.pojo.GenderType;
import validator.pojo.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by why on 11/12/2016.
 */
public class ValidationTest {


    @Test
    public void test4() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
//                .addMapping( (ConstraintMapping) null )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Person>> constraintViolations = validator.validateValue(Person.class, "gender", GenderType.MALE);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations);
    }
}
