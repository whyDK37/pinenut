package util;

import org.testng.annotations.Test;

import java.util.Stack;

/**
 * Created by why on 4/28/2017.
 */
public class StackTest {

    @Test
    public void test() {
        Stack<String> stack = new Stack();

        stack.add("a");
        stack.add(0, "b");


        stack.peek();
    }
}
