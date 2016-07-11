package framework.recipes.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by drug on 2016/5/13.
 */
public class UtilSchema {
    private int age;
    private String name;
    protected UtilSchema empty;
    public static final String staticfield = "static field.";

    public UtilSchema(){}

    public UtilSchema(String name){

    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("util-schema.xml", UtilSchema.class);
        List<Integer> arrayList = print(ctx.getBean("arrayList", List.class));
        List<Integer> linkedList = print(ctx.getBean("linkedList", List.class));

        Set<Integer> hashSet = print(ctx.getBean("hashSet", Set.class));
        Set<Integer> treeSet = print(ctx.getBean("treeSet", Set.class));

        Map<Integer, Integer> hashMap = print(ctx.getBean("hashMap", Map.class));
        Map<Integer, Integer> hashTable = print(ctx.getBean("hashTable", Map.class));

        System.out.println("static field : " + ctx.getBean("staticfield"));

        System.out.println("property path:");
        System.out.println(ctx.getBean("utilSchema.age"));
        System.out.println(ctx.getBean("age"));


        System.out.println("done.");
    }

    private static List<Integer> print(List<Integer> list) {
        System.out.println(list.getClass().toString());
        for (Integer integer : list) {
            System.out.println(integer);
        }
        System.out.println("===================");

        return list;
    }

    private static Set<Integer> print(Set<Integer> set) {
        System.out.println(set.getClass().toString());
        for (Integer integer : set) {
            System.out.println(integer);
        }
        System.out.println("===================");

        return set;
    }

    private static Map<Integer, Integer> print(Map<Integer, Integer> map) {
        System.out.println(map.getClass().toString());
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("===================");

        return map;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmpty(UtilSchema empty) {
        this.empty = empty;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public UtilSchema getEmpty() {
        return empty;
    }
}
