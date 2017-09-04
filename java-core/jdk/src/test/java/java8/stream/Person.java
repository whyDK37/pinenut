package java8.stream;

public class Person {
  public int no;
  private String name;

  private int age;

  public Person(int no, String name) {
    this.no = no;
    this.name = name;
  }

  public Person(int no, String name, int age) {
    this.no = no;
    this.name = name;
    this.age = age;
  }

  public String getName() {
    System.out.println(name);
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Person{");
    sb.append("no=").append(no);
    sb.append('}');
    return sb.toString();
  }
}