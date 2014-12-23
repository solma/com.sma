package com.shuoma.alg.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class NaturalNumberDecomposition {
  public static void main(String[] args) {
    new NaturalNumberDecomposition().main(5);
  }

  public void main(int n) {
    for (ArrayList<Integer> decomp : decompose(n))
      System.out.println(decomp);


    // HashMap<Integer, HashSet<Decomposition> > allDecomposes=new HashMap<Integer,
    // HashSet<Decomposition> >();
    // decompose(n, allDecomposes);
    // for(Decomposition decomp: allDecomposes.get(n)){
    // System.out.println(decomp);
    // }
  }


  public ArrayList<ArrayList<Integer>> decompose(int n) {
    return decomposeRecursion(n, 1, new ArrayList<Integer>(), 0);
  }

  public ArrayList<ArrayList<Integer>> decomposeRecursion(int target, int s, ArrayList<Integer> cur, int lvl) {

    ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

    if (target == 0) {
      ret.add(new ArrayList<Integer>(cur));
      return ret;
    }

    for (; s <= target; s++) {
      cur.add(s);

      for (int j = 0; j < lvl; j++)
        System.out.print("\t");
      System.out.println(target + "  " + s + "  " + cur);

      ret.addAll(decomposeRecursion(target - s, s, cur, lvl + 1));
      cur.remove(cur.size() - 1);
    }
    return ret;
  }


  class Decomposition {
    ArrayList<Integer> seq;

    public Decomposition(ArrayList<Integer> ori) {
      seq = new ArrayList<Integer>(ori);
    }

    @Override
    public boolean equals(Object decomp) {
      if (decomp instanceof Decomposition) {
        HashSet<Integer> thisSeq = new HashSet<Integer>();
        thisSeq.addAll(seq);

        HashSet<Integer> other = new HashSet<Integer>();
        other.addAll(((Decomposition) decomp).seq);

        return thisSeq.equals(other);
      }
      return false;
    }

    @Override
    public int hashCode() {
      int hash = 1;
      for (Integer ele : seq) {
        hash += 17 * ele;
      }
      return hash;
    }

    @Override
    public String toString() {
      Collections.sort(seq);
      return seq.toString();
    }
  }



  void decompose(int n, HashMap<Integer, HashSet<Decomposition>> allDecomposes) {
    HashSet<Decomposition> setForN = new HashSet<Decomposition>();

    if (n > 0) {
      decompose(n - 1, allDecomposes);
      for (int i = 0; i <= n - 1; i++) {
        for (Decomposition decomp : allDecomposes.get(i)) {
          ArrayList<Integer> cpy = new ArrayList<Integer>(decomp.seq);
          cpy.add(n - i);
          setForN.add(new Decomposition(cpy));
        }
      }
    } else {
      ArrayList<Integer> firstSet = new ArrayList<Integer>();
      // firstSet.add(0);
      setForN.add(new Decomposition(firstSet));
    }

    allDecomposes.put(n, setForN);
  }
}
