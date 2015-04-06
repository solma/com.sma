package lang

import com.shuoma.learning.Queue
import org.scalatest.FunSuite;

class QueueTest extends FunSuite{

  test("Enqueue") {
    val q = Queue(1, 2, 3)
    println(q.head)
  }
}
