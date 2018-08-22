package java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangxu
 */
public class Test {
  private String[] states = new String[]{"AK", "AL"};

  public static void print() {
    System.out.println("not null.");
  }

  public static void main(String[] args) throws InterruptedException {
    int[] ints = new Solution().twoSum(new int[]{2, 5, 5, 11}, 10);
    System.out.println(ints[0]);
    System.out.println(ints[1]);
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");
    strings.stream().map(s -> s.toLowerCase())
            .sorted((a, b) -> Integer.compare(b.length(), a.length())).collect(Collectors.toList()).forEach(
            System.out::println);

    System.out.println("for each");
    strings.forEach(s -> System.out.println(s));
    // comment here
    for (String string : strings) {
      System.out.println(string);
    }

    for (int i = 0; i < 10; ++i) {
      System.out.println(i);
    }

    ((Test) null).print();
    int x, y, z, t;
    x = y = z = 1;
    t = ++x | ++y & ++z;
    System.out.println(t);
  }

  public String[] getStates() {
    String[] tmp = new String[states.length];
    System.arraycopy(states, 0, tmp, 0, states.length);
    return tmp;
  }

  static class Solution {
    public int[] twoSum(int[] nums, int target) {
      // key: value, value:index
      Map<Integer, Integer> m = new HashMap<>();
      int[] result = null;
      for (int f = 0; f < nums.length - 1; f++) {
        if (!m.containsKey(nums[f])) {
          m.put(target - nums[f], f);
        } else {
          Integer integer = m.get(target - nums[f]);
          result = new int[]{integer, f};
          break;
        }
      }

      return result;
    }
  }
}
