package scala

/**
  * 换零钱问题
  * https://www.ibm.com/developerworks/cn/java/j-lo-funinscala1/
  */
object CountChange {

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0)
      1
    else if (coins.isEmpty || money < 0)
      0
    else
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }

  def main(args: Array[String]): Unit = {
    val coins: List[Int] = List(1, 5, 10, 50, 100)
    val i = countChange(5, coins)
    print(i)
  }
}
