package validator;

import org.testng.annotations.Test;
import validator.group.NotNullG;
import validator.group.SizeG;
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
public class ValidatorTest {

  @Test
  public void test1() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Person person = new Person(null, 120);
    Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
    System.out.println(constraintViolations);
    assertEquals(1, constraintViolations.size());
  }

  @Test
  public void test2() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Person person = new Person(null, 120);
    Set<ConstraintViolation<Person>> constraintViolations = validator.validateProperty(person, "age");
    assertEquals(1, constraintViolations.size());
    System.out.println(constraintViolations);

    constraintViolations = validator.validateValue(Person.class, "name", null);
    assertEquals(1, constraintViolations.size());
    System.out.println(constraintViolations);
  }

  @Test
  public void test3() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Person person = new Person(null, 120);
    Set<ConstraintViolation<Person>> constraintViolations = validator.validateValue(Person.class, "group", null, NotNullG.class);
    assertEquals(1, constraintViolations.size());
    System.out.println("validate NotNullG |" + constraintViolations);
    constraintViolations = validator.validateValue(Person.class, "group", null, SizeG.class);
    assertEquals(0, constraintViolations.size());
    System.out.println("validate SizeG |" + constraintViolations);
    constraintViolations = validator.validateValue(Person.class, "group", "test", SizeG.class, NotNullG.class);
    assertEquals(1, constraintViolations.size());
    System.out.println("validate NotNullG&SizeG |" + constraintViolations);
  }

  @Test
  public void test4() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    System.err.println(validator);
    Person person = new Person(null, 120);
    Set<ConstraintViolation<Person>> constraintViolations = validator.validateValue(Person.class, "gender", GenderType.MALE);
    assertEquals(1, constraintViolations.size());
    System.out.println(constraintViolations);
  }
}
