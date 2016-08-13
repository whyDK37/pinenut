package framework.jdbc;

/**
 * Created by whydk on 2016/3/26.
 */
public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private String specialty;
    private int age;

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialty() {

        return specialty;
    }

    public int getAge() {
        return age;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", age=" + age +
                '}';
    }
}
