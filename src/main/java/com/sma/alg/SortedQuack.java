package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.MonotonicSequence;
import static com.sma.annotation.Tag.DataStructure.QueueT;
import static com.sma.annotation.Tag.Reference.Interview;
import static com.sma.annotation.Tag.Trick.TwoOrMorePointers;

import com.sma.annotation.Tag;

import java.util.Arrays;
import java.util.Random;

/**
 * consider without duplication case: when you pop() on element A, then peek() next element B.
 * If A is pop from head, B is A's next or last, and A < B, and A should put in the front.
 * If A is pop from tail, B is A's previous or first, and A > B, and A should put in the end.
 * So keep two pointer: front and back, when A > B, array[front++] = A, if A < B, array[back--] = A.
 * If with duplication, have the case B == A, so use a counter to count how many equal element, until
 * find a B != A, when put A, also put the equal elements.
 */
@Tag(dss = {QueueT, MonotonicSequence}, references = Interview, tricks = TwoOrMorePointers)
public class SortedQuack {
  public int[] convert(Quack quack) {
    int[] array = new int[quack.size()];
    int front = 0, end = array.length - 1;
    int count = 0, element = 0;
    while (quack.size() > 0) {
      element = quack.pop();
      int next = quack.peek();
      if (element == next) {
        count++;
      } else if (element > next) {
        array[end--] = element;
        while (count > 0) {
          array[end--] = element;
          count--;
        }
      } else {
        array[front++] = element;
        while (count > 0) {
          array[front++] = element;
          count--;
        }
      }
    }
    while (count-- > 0)
      array[front++] = element;
    return array;
  }

  public static void main(String[] args) {
    SortedQuack converter = new SortedQuack();
    int[] elements = new int[] {1, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9, 9};
    Quack quack = new Quack(14);
    for (int i = 0; i < 14; i++)
      quack.push(elements[i]);

    int[] array = converter.convert(quack);
    System.out.println(Arrays.toString(array));
  }
}


class Quack {
  static int UNSET = -1;
  static int HEAD = 0;

  Random random = new Random();
  int visit = -1;

  int[] elements;
  int head, tail;
  int size;

  public Quack(int capacity) {
    elements = new int[capacity];
    head = 0;
    tail = 0;
    size = 0;
  }

  public void push(int element) {
    elements[tail++] = element;
    size++;
  }

  public int peek() {
    visit = random.nextInt(2);
    if (visit == HEAD)
      return elements[head];
    else
      return elements[tail - 1];
  }

  public int pop() {
    if (visit == UNSET)
      visit = random.nextInt(2);
    int element;
    if (visit == HEAD)
      element = elements[head++];
    else
      element = elements[--tail];
    size--;
    visit = UNSET;
    return element;
  }

  public int size() {
    return size;
  }
}
