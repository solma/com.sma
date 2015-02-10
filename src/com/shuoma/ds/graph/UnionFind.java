package com.shuoma.ds.graph;

import java.util.LinkedList;
import java.util.List;

public class UnionFind<T> {
  public static class Element<T> {
    int groupId;
    T val;

    public Element(int groupId, T val) {
      this.groupId = groupId;
      this.val = val;
    }

    @Override
    public String toString() {
      return val.toString();
    }
  }

  public static class Group {
    List<Element> elements;
    int groupId;
    boolean merged;

    public Group(Element e) {
      this.elements = new LinkedList<>();
      elements.add(e);
      this.groupId = e.groupId;
    }

    public void add(Group g) {
      elements.addAll(g.elements);
      g.merged = true;
      for (Element e : g.elements){
        e.groupId = groupId;
      }
    }

    public int size() {
      return elements.size();
    }

    @Override
    public String toString() {
      return elements.toString();
    }
  }

  public static void main(String[] args) {
    String[] vals = new String[]{"a", "b", "c", "d"};
    List<Element> elements = new LinkedList<>();
    int groupId = 0;
    for (String val : vals) {
      elements.add(new Element(groupId++, val));
    }

    UnionFind<String> ins = new UnionFind<String>(elements);
    ins.union(elements.get(0), elements.get(3));
    ins.union(elements.get(1), elements.get(2));
    ins.union(elements.get(0), elements.get(1));
    System.out.println(ins);
  }

  List<Group> groups;

  public UnionFind (List<Element> elements) {
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

  public void union(Element e1, Element e2) {
    Group g1 = groups.get(find(e1));
    Group g2 = groups.get(find(e2));

    Group larger, smaller;
    if (g1.size() > g2.size()) {
      larger = g1;
      smaller = g2;
    } else {
      larger = g2;
      smaller = g1;
    }

    larger.add(smaller);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Group group : groups)
      if (!group.merged)
        sb.append(group.toString() + "\n");
    return sb.toString();
  }
}
