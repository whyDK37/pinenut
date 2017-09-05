package com.google.gson.tutorial.s;

import com.google.gson.Gson;


/**
 * https://www.tutorialspoint.com/gson/index.htm
 */
public class GsonSerializationNextedInnerClassTester {

  public static void main(String args[]) {
    Student student = new Student();
    student.setRollNo(1);
//    Student.Name name = student.new Name();
    Student.Name name = new Student.Name();

    name.firstName = "Mahesh";
    name.lastName = "Kumar";
    student.setName(name);
    Gson gson = new Gson();

    String jsonString = gson.toJson(student);
    System.out.println(jsonString);
    student = gson.fromJson(jsonString, Student.class);

    System.out.println("Roll No: " + student.getRollNo());
    System.out.println("First Name: " + student.getName().firstName);
    System.out.println("Last Name: " + student.getName().lastName);

    String nameString = gson.toJson(name);
    System.out.println(nameString);

    name = gson.fromJson(nameString, Student.Name.class);
    System.out.println(name.getClass());
    System.out.println("First Name: " + name.firstName);
    System.out.println("Last Name: " + name.lastName);
  }


}

class Student {
  private int rollNo;
  private Name name;

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  //  class Name {
  static class Name {
    public String firstName;
    public String lastName;
  }
}