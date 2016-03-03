package me.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by whydk on 2015/11/22.
 */
public class Test {
    public static void main(String[] args) {
        FileFilter java = (File f) -> f.getName().endsWith("*.java");

        File dir = new File("/");
        FileFilter directoryFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        dir = new File("/");
        directoryFilter = (File f) -> f.isDirectory();
        File[] dirs = dir.listFiles(directoryFilter);

//        进一步简化：
        dir = new File("/");
        dirs = dir.listFiles(f -> f.isDirectory());

        Callable<String> c = () -> "done";


        List<Person> people = new ArrayList<>();
        Collections.sort(people, Comparator.comparing((Person p) -> p.getLastName()));
        //在类型推导和静态导入的帮助下，我们可以进一步简化上面的代码：
        Collections.sort(people, Comparator.comparing(p -> p.getLastName()));
        //我们注意到这里的lambda表达式实际上是getLastName的代理（forwarder），于是我们可以用方法引用代替它：
        Collections.sort(people, Comparator.comparing(Person::getLastName));


        people.forEach(p -> System.out.println(p));

        //Before Java 8:
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8 way:
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

        //In Java 8:
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(System.out::println);
        features.forEach(n -> {
            System.out.println(n.getClass());
            System.out.println(n);
        });
    }

    class Person {
        private String lastName;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
