package com.google.gson.tutorial.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CustomAdapterTester {

  public static void main(String args[]) {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Student.class, new StudentAdapter());
    builder.setPrettyPrinting();
    Gson gson = builder.create();

    String jsonString = "{\"name\":\"Mahesh\", \"rollNo\":1}";
    Student student = gson.fromJson(jsonString, Student.class);
    System.out.println(student);

    jsonString = gson.toJson(student);
    System.out.println(jsonString);
  }
}

class StudentAdapter extends TypeAdapter<Student> {
  @Override
  public Student read(JsonReader reader) throws IOException {
    Student student = new Student();
    reader.beginObject();
    String fieldname = null;

    while (reader.hasNext()) {
      JsonToken token = reader.peek();

      if (token.equals(JsonToken.NAME)) {
        //get the current token
        fieldname = reader.nextName();
      }
      if ("name".equals(fieldname)) {
        //move to next token
        token = reader.peek();
        student.setName(" from-adapter - " + reader.nextString());
      }
      if ("rollNo".equals(fieldname)) {
        //move to next token
        token = reader.peek();
        student.setRollNo(reader.nextInt());
      }
    }
    reader.endObject();
    return student;
  }

  @Override
  public void write(JsonWriter writer, Student student) throws IOException {
    writer.beginObject();
    writer.name("name");
    writer.value("c -" + student.getName());
    writer.name("rollNo");
    writer.value(student.getRollNo());
    writer.endObject();
  }
}

class Student {
  private int rollNo;
  private String name;

  public int getRollNo() {
    return rollNo;
  }

  public void setRollNo(int rollNo) {
    this.rollNo = rollNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "Student[ name = " + name + ", roll no: " + rollNo + "]";
  }
}