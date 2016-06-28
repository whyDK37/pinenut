package c0;

/**
 * Created by drug on 2016/3/18.
 */
public class User implements ShowMe{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
    public void showMe(){
        System.out.println("i am user");
    }
}
