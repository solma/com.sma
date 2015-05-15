package lang.reflect

import scala.reflect.runtime.universe._
import scala.reflect._

object TypeTag {

  val separator =  " | "

  def main (args: Array[String]) {
    basics()
  }

  def basics(): Unit = {
    val ta = typeOf[Array[Array[Int]]]
    val tb = typeTag[Array[Array[Int]]].tpe
    println(ta + separator + tb + separator + (ta =:= tb))

    val ca = classTag[Array[Array[Int]]]
    println(classOf[Array[Array[Int]]] + separator + ca + separator + ca.runtimeClass)

    println(typeOf[Seq[Int]] + separator + classOf[Seq[Int]])
  }
}
