package com.sma.alg;

import static com.sma.annotation.Tag.DataStructure.IteratorT;
import static com.sma.annotation.Tag.DataStructure.QueueT;
import static com.sma.annotation.Tag.Reference.LeetCode;

import com.sma.annotation.Tag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 Given an Iterator class interface with methods: next() and hasNext(),
 design and implement a PeekingIterator that support the peek() operation
 it essentially peek() at the element that will be returned by the next call to next().

 Example:
 Assume that the iterator is initialized to: [1, 2, 3].
 1) Call next() gets you 1.
 2) Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 3) You call next() the final time and it returns 3, the last element.
 4) Calling hasNext() after that should return false.
 */
@Tag(dss = {IteratorT, QueueT}, references = LeetCode)
public class PeekingIterator implements Iterator<Integer> {
  Iterator<Integer> it;
  int top;
  boolean alreadyPeeked;

  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    it = iterator;
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() throws NoSuchElementException {
    if (alreadyPeeked) { return top; }
    else {
      if (!hasNext()) { throw new NoSuchElementException(); }
      top = it.next();
      alreadyPeeked = true;
      return top;
    }
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    int ret;
    if (alreadyPeeked) {
      ret = top;
    } else {
      if (!hasNext()) { throw new NoSuchElementException(); }
      ret = it.next();
    }
    alreadyPeeked = false;
    return ret;
  }

  @Override
  public boolean hasNext() {
    return alreadyPeeked || it.hasNext(); // return true after last element peeks
  }

  @Override
  public void remove() {
    it.remove();
  }
}
