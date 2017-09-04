package data_structures;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

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

  /**
   * 四则运算，把中缀表达式转化为后缀表达式
   * 输入：9+(3-1)*3+10/2
   * 输出：9 3 1 - 3 * + 10 2 / +
   */
  @Test
  public void testCalc() {
    String[] mathString = "9+(3-1)*3+10/2".split("");

    Pattern digitPattern = Pattern.compile("^[0-9]\\d*$");
//        System.out.println(digitPattern.matcher("").matches());

    boolean digit;
    boolean symbol = false;

    MyArrayStack<String> stack = new MyArrayStack<>(50);
    for (String s : mathString) {
      digit = digitPattern.matcher(s).matches();
      if (digit) {
        System.out.print(s);
        if (symbol) {
          System.out.print(" ");
        }
        symbol = false;
      } else {
        stack.push(s);
        symbol = true;
      }

      if (")".equals(stack.peek())) {
        while (true) {
          String pop = stack.pop();
          if (")".equals(pop))
            continue;
          else if ("(".equals(pop))
            break;
          System.out.print(" " + pop + " ");
        }
      }
    }
  }

}