package scala

/**
  * 换零钱问题
  * https://www.ibm.com/developerworks/cn/java/j-lo-funinscala1/
  */
object CountChange {

  def countChange(money: Int, coins: List[Int]): Int = {
    // 首先确定边界条件，如果要兑换的钱数为 0，那么返回 1，即只有一种兑换方法：没法兑换。
    // 这里要注意的是该问题计算所有的兑换方法，无法兑换也算一种方法。
    if (money == 0)
      1
    //如果零钱种类为 0 或钱数小于 0，没有任何方式进行兑换，返回 0。
    else if (coins.isEmpty || money < 0)
      0
    else
    //      我们可以把找零的方法分为两类：使用不包含第一枚硬币（零钱）所有的零钱进行找零，使用包含第一枚硬币（零钱）的所有零钱进行找零，
    // 两者之和即为所有的找零方式。
    // 第一种找零方式总共有 countChange(money, coins.tail)种，
    // 第二种找零方式等价为对于 money – conins.head进行同样的兑换，
    // 则这种兑换方式有 countChange(money - coins.head, coins)种，两者之和即为所有的零钱兑换方式。
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }

  def main(args: Array[String]): Unit = {
    val coins: List[Int] = List(1, 2, 5, 10, 50, 100)
    val i = countChange(5, coins)
    print(i)

    print("\n")
    print(coins.tail)
  }
}
