package scala

import scala.annotation.tailrec

/**
  * 换零钱问题
  * https://www.ibm.com/developerworks/cn/java/j-lo-funinscala1/
  */
object Test {

  //xs.head 返回列表里的头元素，即第一个元素
  //xs.tail 返回除头元素外的剩余元素组成的列表
  def sum(xs: List[Int]): Int =
  if (xs.isEmpty) 0 else xs.head + sum(xs.tail)

  //  求最大值
  def max(xs: List[Int]): Int = {
    if (xs.isEmpty)
      throw new java.util.NoSuchElementException
    if (xs.size == 1)
      xs.head
    else if (xs.head > max(xs.tail)) xs.head else max(xs.tail)
  }

  //  反转字符串
  def reverse(xs: String): String =
    if (xs.length == 1) xs else reverse(xs.tail) + xs.head

  //  快速排序
  def quickSort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) xs
    else
      quickSort(xs.filter(x => x < xs.head)) ::: xs.head :: quickSort(xs.filter(x => x > xs.head))
  }

  //  递归求阶乘
  def factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial(n - 1)

  //  尾递归求阶乘
  def factorial2(n: Int): Int = {
    @tailrec
    def loop(acc: Int, n: Int): Int =
      if (n == 0) acc else loop(n * acc, n - 1)

    loop(1, n)
  }

  def main(args: Array[String]): Unit = {

  }
}