package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.Greedy;
import static com.shuoma.annotation.Tag.DataStructure.Array;

import com.shuoma.annotation.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Task Scheduler: Given a sequence of tasks: AABABCD. Same task have cool down time before
 * executed again. So the sequence can be executed as A__AB_ABCD.
 * Given a sequence, write code to find out total time need.
 */
@Tag(algs = Greedy, dss = Array)
public class TaskScheduler {
  public int totalTime(String task, int coolDown) {
    Map<Character, Integer> earliestTime = new HashMap<>();
    int scheduledIdx = -1;

    for (int i = 0; i < task.length(); i++) {
      char ch = task.charAt(i);

      //check how to schedule task[i]
      int earliest = earliestTime.containsKey(ch) ? earliestTime.get(ch) : 0;
      if (earliest > scheduledIdx) scheduledIdx = earliest;   //no earlier than earliest
      else scheduledIdx++;

      //update the earliest time of next task same as task[i]
      System.out.println(ch + " " + scheduledIdx + " " + earliest);
      earliestTime.put(ch, scheduledIdx + coolDown + 1);
    }
    return scheduledIdx + 1;
  }

  public static void main(String[] args) {
    TaskScheduler scheduler = new TaskScheduler();
    System.out.println(scheduler.totalTime("AABABCD", 2));  //10
  }
}
