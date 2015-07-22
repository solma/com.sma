package lang.collection.map

import java.util.{Map => JMap}

import scala.collection.JavaConversions._

object MapBehavior {

  def main(args: Array[String]): Unit = {
    println(mapToJMap(stringToMap()))
  }

  def stringToMap(): Map[String, String] = {
    val input = "model:String country_code:String area_code:String area_name:String device_cnt:String"
    input.replaceAll("\\s+", " ").split(" ").map(f => {val parts = f.split(":"); (parts(0), parts(1))}).toMap
  }

  def mapToJMap(m: Map[String, String]): JMap[String, String] = {
    println(m)
    mapAsJavaMap(m)
  }
}
