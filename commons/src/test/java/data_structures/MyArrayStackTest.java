package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by why on 4/28/2017.
 */
public class MyArrayStackTest {
    @Test
    public void testPush() throws Exception {

        MyArrayStack<String> stack = new MyArrayStack(20);
        stack.push("a");
        stack.push("b");
        stack.push("c");

        System.out.println(stack.size());

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Assert.assertEquals(stack.size, 0);

    }

}