package jdk.instrument;

import java.lang.instrument.Instrumentation;

public class MySizeOf {
  private static Instrumentation inst;

  /**
   * 这个方法必须写，在agent调用时会被启用
   */
  public static void premain(String agentArgs, Instrumentation instP) {
    inst = instP;
  }

  /**
   * 直接计算当前对象占用空间大小，包括：当前类及超类的基本类型实例字段大小
   * 引用类型实例字段引用大小、实例基本类型数组总占用空间、实例引用类型数组引用本身占用空间大小
   * 但是不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小
   * 用来测量java对象的大小（这里先理解这个大小是正确的，后面再深化）
   */
  public static long sizeOf(Object o) {
    if (inst == null) {
      throw new IllegalStateException("Can not access instrumentation environment.\n" +
              "Please check if jar file containing SizeOfAgent class is \n" +
              "specified in the java's \"-javaagent\" command line argument.");
    }
    return inst.getObjectSize(o);
  }

  public static void main(String[] args) {
    System.out.println(MySizeOf.sizeOf(new Integer(1)));
    System.out.println(MySizeOf.sizeOf(new String("a")));
    System.out.println(MySizeOf.sizeOf(new char[1]));
  }
}