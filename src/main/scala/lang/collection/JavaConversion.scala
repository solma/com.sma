package lang.collection

import java.util.Map
import java.util.Set
import java.util.HashMap

import scala.collection.JavaConversions._
object JavaConversion {

  def main (args: Array[String]) {
    val jMap: Map[String, String] = createJavaMap()
    javaMapIterate(jMap)
    javaMapEntrySetIterate(jMap.entrySet())
  }

  def createJavaMap(): Map[String, String] = {
    val jMap: Map[String, String] = new HashMap[String, String]()
    jMap.put("k1", "v1")
    jMap.put("k2", "v2")
    jMap
  }

  def javaMapIterate(jMap: Map[String, String]): Unit = {
    jMap.foreach(kv => {
      kv._1 match {
        case key if key.startsWith("k1") => println(kv)
        case _ =>
      }
    })
  }

  def javaMapEntrySetIterate(jMapEntrySet: Set[Map.Entry[String, String]]): Unit = {
    jMapEntrySet.foreach(kv => {
      kv.getKey match {
        case key if key.startsWith("k2") => println(kv.getValue)
        case _ =>
      }
    })
  }
}
