package buffer;

import java.util.ArrayList;
import java.util.Scanner;

public class Greedy2 {
  public static void main(String[] args) {
    String str = "";
    ArrayList<String> array = new ArrayList<String>();
    Scanner in = new Scanner(System.in);
    System.out.println("Please input the number of data:");
    int n = in.nextInt();
    System.out.println("Please input the data:");
    while (n-- > 0) {
      array.add(in.next());
    }
    for (int i = 0; i < array.size(); i++)
      for (int j = i + 1; j < array.size(); j++) {
        if ((array.get(i) + array.get(j)).compareTo(array.get(j) + array.get(i)) < 0) {
          String temp = array.get(i);
          array.set(i, array.get(j));
          array.set(j, temp);
        }
      }
    for (int i = 0; i < array.size(); i++) {
      str += array.get(i);
    }
    System.out.println("str=:" + str);
  }
}