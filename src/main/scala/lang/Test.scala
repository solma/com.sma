package lang

import com.shuoma.learning.Queue

import scala.reflect.runtime.universe._

object Test {
  def main(args: Array[String]): Unit = {
    val q = Queue(1, 2, 3)
    //if (q.isInstanceOf[Queue[Float]]) {
    if (matchList(q)) {
      println(q)
    }

    if (q.isInstanceOf[Queue[Long]]) {
      println(q + " is instance of Queue[Int]")
    }
  }

  def matchList[A: TypeTag](q: Queue[A]) : Boolean = q match {
    case intQueue if typeOf[A] =:= typeOf[Int] => true
    case _ => false
  }
}
