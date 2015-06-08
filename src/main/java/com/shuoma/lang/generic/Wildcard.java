package com.shuoma.lang.generic;

import java.util.LinkedList;
import java.util.List;

// http://tutorials.jenkov.com/java-generics/wildcards.html
public class Wildcard {
  public static void main(String[] args) {
    new Wildcard().main();
  }

  void main() {
    List<A> la = new LinkedList<>();
    List<B> lb = new LinkedList<>();
    List<C> lc = new LinkedList<>();

    // not compilable
    // la = lb;
    // lb = la;

    readList(la);
    readList(lb);
    readList(lc);

    writeList(la);

    // not compilable
    // writeList(lb);
    // writeList(lc);

    readList1(lc);
  }

  void readList1(List<?> li) {
    for (Object o : li) {
      System.out.println(o);
    }
  }

  void readList(List<? extends A> li) {
    for (A ele : li) {
      System.out.println(ele);
    }

    // not compilable
//     li.add(new B());
//     li.add(new C());
  }

  void writeList(List<? super A> li) {
    li.add(new A());
    li.add(new B());
    li.add(new C());

    Object o = li.get(0);

    // not compilable
    // A ele = li.get(0);
  }
}

class A {
}

class B extends A {
}

class C extends A {
}
