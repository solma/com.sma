package lang.concurrent

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem
import akka.routing.RoundRobinPool

/**
 * Actor Example
 * reference: http://zhuanlan.zhihu.com/guagua/20009659
 */
object Sum {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MasterActorSystem")
    val masterActor = system.actorOf(Props[MasterActor], name = "masterActor")
    masterActor ! "calculate..."
    Thread.sleep(5000)
  }
}

sealed trait SumTrait
case class Result(value: Int) extends SumTrait

// compute actor
class SumActor extends Actor {
  val RANGE = 10000

  def calculate(start: Int, end: Int, flag : String): Int = {
    var cal = 0

    for (i <- (start to end)) {
      for (j <- 1 to 3000000) {}
      cal += i
    }

    println("flag : " + flag + ".")
    return cal
  }

  def receive = {
    case value: Int =>
      sender ! Result(calculate((RANGE / 4) * (value - 1) + 1, (RANGE / 4) * value, value.toString))
    case _ => println("unknown in SumActor...")
  }
}

// print actor
class PrintActor extends Actor {
  def receive = {
    case (sum: Int, startTime: Long) =>
      println("Sum：" + sum + "；TimeSpent："
        + (System.nanoTime() - startTime)/1000000000.0 + "sec.")
    case _ => println("unknown in PrintActor...")
  }
}

// master actor，send compute commands to SumActor，and print commands to PrintActor
class MasterActor extends Actor {
  var sum = 0
  var count = 0
  var startTime: Long = 0

  // declare Actor instance，nrOfInstances is the number of routee（SumActor) in the pool
  val sumActor   = context.actorOf(Props[SumActor]
    .withRouter(RoundRobinPool(nrOfInstances = 4)), name = "sumActor")
  val printActor = context.actorOf(Props[PrintActor], name = "printActor")

  def receive = {
    case "calculate..." =>
      startTime = System.nanoTime()
      for (i <- 1 to 4) sumActor ! i
    case Result(value) =>
      sum += value
      count += 1
      if (count == 4) {
        printActor ! (sum, startTime)
        context.stop(self)
      }
    case _ => println("unknown in MasterActor...")
  }
}
