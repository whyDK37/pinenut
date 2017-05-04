package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by why on 4/28/2017.
 */
public class MyLinkedStackTest {
    @Test
    public void testPush() throws Exception {
        MyLinkedStack<String> stack = new MyLinkedStack();

        stack.push("a");
        stack.push("b");
        stack.push("c");

        System.out.println(stack.size());

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Assert.assertEquals(stack.size(), 0);
    }

}