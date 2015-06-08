import scala.reflect._
import scala.reflect.runtime.universe._

println(typeOf[Seq[Int]] + " " + classOf[Seq[Int]])
def returnArrayWithClassTag[T: ClassTag](s: String): Array[T] = Array[T]()
//returnArrayWithClassTag("s")
returnArrayWithClassTag[String]("s").map(x => x.length)
//returnArrayWithClassTag[Int]("s").map(x => x.length) // won't compile
def getTypeTag[T: TypeTag](ary: Array[T]): Type = typeOf[T]
getTypeTag(Array(1, 2, 3))
getTypeTag[Int](returnArrayWithClassTag[Int]("s"))
val a = Array(1, 2, 3)
println(a.toList)
val li =  List('c','a','b')
li.zipWithIndex.sortBy(_._1)
Array.concat(Array(Array(1, 2), Array(7, 8)): _*).mkString(",")


def f[T](v: T)(implicit ev: ClassTag[T]) = ev.toString
f("s": Any)
