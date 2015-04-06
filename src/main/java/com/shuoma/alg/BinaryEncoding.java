package com.shuoma.alg;

import static com.shuoma.annotation.Tag.Algorithm.DivideConquer;
import static com.shuoma.annotation.Tag.DataStructure.PriorityQueue;
import static com.shuoma.annotation.Tag.DataStructure.String;
import static com.shuoma.annotation.Tag.Difficulty.D3;

import com.shuoma.annotation.Tag;

import java.util.PriorityQueue;

/**
 * Encode symbols in bits based on their frequency
 */
@Tag(algs = DivideConquer, dl = D3, dss = {PriorityQueue, String})
public class BinaryEncoding {
  public class Symbol implements Comparable<Symbol> {
    public String sym;
    public String binEncode;
    public double prob;

    public Symbol(String s, double prob) {
      this(prob);
      this.sym = s;
    }

    public Symbol(double prob) {
      this.prob = prob;
    }

    @Override
    public int compareTo(Symbol other) {
      return (int) (prob - other.prob);
    }

    public String toString() {
      return sym + " " + prob;
    }
  }


  public class SymbolTreeNode implements Comparable<SymbolTreeNode> {
    public Symbol node;
    public SymbolTreeNode left, right;

    public SymbolTreeNode(Symbol sym) {
      this.node = sym;
      left = null;
      right = null;
    }

    @Override
    public int compareTo(SymbolTreeNode other) {
      return node.compareTo(other.node);
    }

    public String toString() {
      return node + " " + left + " " + right;
    }
  }

  public static void main(String[] args) {
    new BinaryEncoding().main();
  }

  public void main() {
    String sym = "ABCDE";
    int[] freq = {15, 7, 6, 6, 5};
    Symbol[] syms = new Symbol[sym.length()];
    for (int i = 0; i < sym.length(); i++) {
      syms[i] = new Symbol(sym.substring(i, i + 1), freq[i]);
    }

    //HuffmanEncoding(syms);
    ShannonFanoEncoding(syms, 0, syms.length - 1, "");

    double weightedSum = 0, sumOfWeight = 0;
    for (int i = 0; i < syms.length; i++) {
      System.out.println(syms[i].sym + " " + syms[i].binEncode);
      weightedSum += syms[i].binEncode.length() * syms[i].prob;
      sumOfWeight += syms[i].prob;
    }
    System.out.print(weightedSum / sumOfWeight);
  }

  public void HuffmanEncoding(Symbol[] syms) {
    PriorityQueue<SymbolTreeNode> pq = new PriorityQueue<>();
    for (int i = 0; i < syms.length; i++)
      pq.add(new SymbolTreeNode(syms[i]));

    while (!pq.isEmpty()) {
      SymbolTreeNode min1 = pq.remove();
      if (pq.isEmpty()) {// min1 is the root of the tree;
        traverse(min1, "");
        break;
      }
      SymbolTreeNode min2 = pq.remove();
      SymbolTreeNode newNode = new SymbolTreeNode(
          new Symbol(min1.node.sym + min2.node.sym, min1.node.prob + min2.node.prob));
      newNode.left = min1;
      newNode.right = min2;
      //System.out.println(min1+" "+min2);
      pq.add(newNode);
    }
  }

  private void traverse(SymbolTreeNode root, String encoding) {
    if (root.right == null && root.left == null) {
      root.node.binEncode = encoding;
      return;
    }
    traverse(root.left, encoding + "0");
    traverse(root.right, encoding + "1");
  }

  public void ShannonFanoEncoding(Symbol[] syms, int l, int r, String encoding) {
    if (l == r) {
      syms[l].binEncode = encoding;
      return;
    }
    int divide = partition(syms, l, r);
    ShannonFanoEncoding(syms, l, divide, encoding + "0");
    ShannonFanoEncoding(syms, divide + 1, r, encoding + "1");
  }

  private int partition(Symbol[] syms, int l, int r) {
    double sumLeft = 0, sumRight = 0;
    l--;
    r++;
    while (l + 1 != r) {
      if (sumLeft < sumRight)
        sumLeft += syms[++l].prob;
      else
        sumRight += syms[--r].prob;
    }
    //System.out.println(l+" "+r);
    return l;
  }
}
