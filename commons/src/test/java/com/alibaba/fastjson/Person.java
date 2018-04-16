package com.alibaba.fastjson;

public class Person {
    private String name;
    private int age;

    private Person son;

    private String no = "no get/set";

    public Person() {
    }

    public Person(String name, int age, Person son) {
        this.name = name;
        this.age = age;
        this.son = son;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public Person getSon() {
        return son;
    }

    public void setSon(Person son) {
        this.son = son;
    }

    public boolean isOldPerson() {
        return this.age >= 60;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", son=").append(son);
        sb.append('}');
        return sb.toString();
    }
}