package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.HashTable;
import static com.shuoma.annotation.Tag.Difficulty.D3;
import static com.shuoma.annotation.Tag.Reference.LeetCode;
import static com.shuoma.annotation.Tag.Trick.ForwardAndBackwardScan;
import static com.shuoma.annotation.Tag.DataStructure.LinkedList;

import com.shuoma.annotation.Tag;

import java.util.HashMap;

@Tag(dl = D3, dss = {LinkedList, HashTable}, reference = LeetCode, tricks = ForwardAndBackwardScan)
public class LRUCache {

  public static void main(String[] args) {
    LRUCache instance = new LRUCache(3);
    int[] hits = {1, 3, 2, 2, 7, 1, 3, 2, 1};
    for (int i : hits) {
      instance.set(i);
      instance.print();
    }
  }

  public void print() {
    if (head == null) {
      return;
    }
    Node cur = head;
    do {
      System.out.print(cur.key + " ");
      cur = cur.next;
    } while (cur != null && cur != head);
    System.out.println();
  }

  //implement bidirectional linkedlist
  class Node {
    Node prev;
    Node next;
    int key;
    int value;
  }


  private Node head = null;
  private HashMap<Integer, Node> map = new HashMap<>();
  private int capacity = 0;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      // refresh the list
      detach(n);
      attach(n);
      return n.value;
    } else {
      return -1;
    }
  }

  public void set(int key) {
    set(key, key);
  }

  public void set(int key, int value) {
    if (map.containsKey(key)) {
      // update the value
      Node n = map.get(key);
      n.value = value;
      // refresh the list
      detach(n);
      attach(n);
    } else {
      // add a new node
      Node n = new Node();
      n.key = key;
      n.value = value;
      attach(n);
      map.put(key, n);
      if (map.size() > capacity) {
        // remove the last node
        Node last = head.prev;
        detach(last);
        map.remove(last.key);
      }
    }
  }

  // attach the given node to the beginning of the list
  private void attach(Node n) {
    if (head != null) {
      n.prev = head.prev;
      head.prev.next = n;
      n.next = head;
      head.prev = n;
      head = n;
    } else {
      head = n;
      head.next = n;
      head.prev = n;
    }
  }

  // detach the given node from the list
  private void detach(Node n) {
    if (n == head)
      head = n.next;
    n.prev.next = n.next;
    n.next.prev = n.prev;
  }



  // TLE
  //    private HashMap<Integer, Integer> values;
  //    private ArrayList<Integer> cache;
  //    private int SIZE;

    /*public LRUCache(int capacity) {
        cache=new ArrayList<Integer>();
        SIZE=capacity;
        values=new HashMap<Integer, Integer>();
    }

    public int get(int key) {
        if(cache.contains(key)) return values.get(key);
        else return -1;
    }

    public void set(int key, int value) {

        if(!cache.contains(key)){
            if(cache.size()==SIZE){
                values.remove(cache.remove(0));
            }
        }else{
            cache.remove((Integer)key);
        }
        cache.add(key);
        values.put(key,value);
    }*/
}
