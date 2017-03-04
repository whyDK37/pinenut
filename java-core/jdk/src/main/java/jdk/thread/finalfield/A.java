package jdk.thread.finalfield;

import java.lang.reflect.Field;

/**
 * 在方法d()中，编译器可以自由地对读x的操作与g()调用进行重排序。这样，A().f()可能会返回-1,0或1.
 */
public class A {
    public static void main(String[] args) {
        int f = new A().f();
        System.out.println(f);
    }

    final int x;

    A() {
        x = 1;
    }

    int f() {
        return d(this, this);
    }

    int d(A a1, A a2) {
        int i = a1.x;
        g(a1);
        int j = a2.x;
        return j - i;
    }

    static void g(A a) {
        // uses reflection to change a.x to 2
        try {
            Field x = a.getClass().getDeclaredField("x");
            x.setAccessible(true);
            x.setInt(a,2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}