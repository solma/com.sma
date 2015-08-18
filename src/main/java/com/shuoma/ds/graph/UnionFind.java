package com.shuoma.ds.graph;

import static com.shuoma.annotation.Tag.DataStructure.UnionFind;

import com.shuoma.annotation.Tag;

import java.util.LinkedList;
import java.util.List;

@Tag(dss = UnionFind)
public class UnionFind {

  public static void main(String[] args) {
    String[] values = new String[] {"a", "b", "c", "d"};
    List<Element> elements = new LinkedList<>();
    int groupId = 0;
    for (String val : values) {
      elements.add(new Element(groupId++, val));
    }

    UnionFind ins = new UnionFind(elements);
    ins.union(elements.get(0), elements.get(3));
    ins.union(elements.get(0), elements.get(2));
    ins.union(elements.get(2), elements.get(3));
    System.out.println(ins);
  }

  List<Group> groups;

  public UnionFind(List<Element> elements) {
    groups = new LinkedList<>();
    for (Element e : elements) {
      groups.add(new Group(e));
    }
  }

  /** Find group id of the element. */
  public int find(Element e) {
    Group group = groups.get(e.groupId);
    while (group.merged) {
      group = groups.get(group.elements.get(0).groupId);
    }
    return group.groupId;
  }

  /** Return true if e1 and e2 belong to different groups and merge the two groups. */
  public boolean union(Element e1, Element e2) {
    Group g1 = groups.get(find(e1));
    Group g2 = groups.get(find(e2));

    if (g1.groupId == g2.groupId)
      return false;

    Group larger, smaller;
    if (g1.compareTo(g2) >= 0) {
      larger = g1;
      smaller = g2;
    } else {
      larger = g2;
      smaller = g1;
    }

    larger.add(smaller);
    return true;
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Group group : groups)
      if (!group.merged)
        sb.append(group.toString() + "\n");
    return sb.toString();
  }
}


class Element<T> {
  int groupId;
  T val;

  public Element(int groupId, T val) {
    this.groupId = groupId;
    this.val = val;
  }

  @Override public String toString() {
    return val.toString();
  }
}


class Group implements Comparable<Group> {
  List<Element> elements;
  int groupId;
  boolean merged;
  int rank;

  public Group(Element e) {
    this.elements = new LinkedList<>();
    elements.add(e);
    this.groupId = e.groupId;
    this.rank = e.groupId;
  }

  public void add(Group g) {
    elements.addAll(g.elements);
    g.merged = true;
    for (Element e : g.elements) {
      e.groupId = groupId;
    }
  }

  public int size() {
    return elements.size();
  }

  @Override public int compareTo(Group g) {
    return rank - g.rank;
  }

  @Override public String toString() {
    return elements.toString();
  }
}
