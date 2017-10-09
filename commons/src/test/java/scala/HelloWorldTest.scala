
package scala

object HelloWorldTest {
  def main(args: Array[String]): Unit = {
    val VALID_GRADES = Set("A", "B", "C", "D", "F")


    def letterGrade(value: Any): String = value match {
      case x: Int if (90 to 100).contains(x) => "A"
      case x: Int if (80 to 90).contains(x) => "B"
      case x: Int if (70 to 80).contains(x) => "C"
      case x: Int if (60 to 70).contains(x) => "D"
      case x: Int if (0 to 60).contains(x) => "F"
      case x: String if VALID_GRADES(x.toUpperCase) => x.toUpperCase
    }

    printf("Amy scores %d and receives %s\n", 91, letterGrade(91))
    printf("Bob scores %d and receives %s\n", 72, letterGrade(72))
    printf("Sam never showed for class, scored %d, and received %s\n",
      44, letterGrade(44))
    printf("Roy transfered and already had %s, which translated as %s\n",
      "B", letterGrade("B"))
  }

  def printColor(c: Color) = c match {
    case Red(v) => println("Red: " + v)
    case Green(v) => println("Green: " + v)
    case Blue(v) => println("Blue: " + v)
    case col: Color => {
      print("R: " + col.red + ", ")
      print("G: " + col.green + ", ")
      println("B: " + col.blue)
    }

    case null => println("invalid color")
  }

  class Color(val red: Int, val green: Int, val blue: Int)

  //  颜色模板
  case class Red(r: Int) extends Color(r, 0, 0)

  case class Green(g: Int) extends Color(0, g, 0)

  case class Blue(b: Int) extends Color(0, 0, b)

}