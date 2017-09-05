package com.google.gson.tutorial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;

public class Student {
  public static String className;
  @Expose
  @Since(1.0)
  private int age;
  @Since(1.0)
  private int rollNo;
  @Expose
  @Since(1.0)
  private String name;
  @Since(1.1)
  private boolean verified;
  private transient int id;

  public Student() {
  }

  public static String getClassName() {
    return className;
  }

  public static void setClassName(String className) {
    Student.className = className;
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

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Student{");
    sb.append("age=").append(age);
    sb.append(", rollNo=").append(rollNo);
    sb.append(", name='").append(name).append('\'');
    sb.append(", verified=").append(verified);
    sb.append(", id=").append(id);
    sb.append('}');
    return sb.toString();
  }
}