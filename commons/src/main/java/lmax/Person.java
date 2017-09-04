package lmax;

import java.util.Date;

public class Person {
  private String name;
  private int age;
  private String gender;
  private String mobile;

  public Person() {
  }

  public Person(String name, int age, String gender, String mobile) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.mobile = mobile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Person{");
    sb.append("name='").append(name).append('\'');
    sb.append(", age=").append(age);
    sb.append(", gender='").append(gender).append('\'');
    sb.append(", mobile='").append(mobile).append('\'');
    sb.append('}').append(new Date());
    return sb.toString();
  }
}