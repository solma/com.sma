package com.shuoma.ds.tree;

import com.shuoma.ds.graph.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree {
  private BstNode root;

  public enum TraversalOrder {
    PREORDER, INORDER, POSTORDER;
  }

  public enum Implementation {
    RECURSION, ITERATIVE;
  }

  public BinarySearchTree() {
    root = null;
  }

  public BinarySearchTree(BstNode root) {
    this.root = root;
  }

  private ArrayList<BstNode> BuildAllBinaryTreesPermutation(int min, int max) {
    ArrayList<BstNode> ret = new ArrayList<BstNode>();
    if (min > max) {
      ret.add(null);
    } else {
      for (int i = min; i <= max; i++) {
        BstNode root = new BstNode(String.valueOf(i), i);
        ArrayList<BstNode> left_trees = BuildAllBinaryTreesPermutation(min, i - 1);
        ArrayList<BstNode> right_trees = BuildAllBinaryTreesPermutation(i + 1, max);
        for (BstNode l : left_trees)
          for (BstNode r : right_trees) {
            BstNode newTree = new BstNode(root);
            ret.add(newTree);
            newTree.left = l;
            newTree.right = r;
          }
      }
    }
    return ret;
  }

  /**
   * For the key values 1...numKeys, how many structurally unique binary search trees are possible
   * that store those keys? Strategy: consider that each value could be the root. Recursively find
   * the size of the left and right subtrees.
   */
  public int countTree(int N) {
    if (N <= 1) return 1;
    int sum = 0;
    for (int i = 1; i <= N; i++) {
      sum += countTree(i - 1) * countTree(N - i);
    }
    return sum;
  }

  private BstNode[] flattenRecursivePreorder(BstNode cur) {
    BstNode[] firstAndLast = new BstNode[2];
    firstAndLast[0] = firstAndLast[1] = cur;
    BstNode[] next;

    BstNode firstOfRightSubtree = null;
    if (cur.right != null) {
      next = flattenRecursivePreorder(cur.right);
      firstAndLast[1] = next[1];
      firstOfRightSubtree = next[0];
    }
    if (cur.left != null) {
      next = flattenRecursivePreorder(cur.left);
      cur.left = null;
      cur.right = next[0];
      next[1].right = firstOfRightSubtree; // connecting left and right subtrees
      if (firstAndLast[1].equals(cur)) firstAndLast[1] = next[1]; // update last
      // firstAndLast[0]=cur; //update first
    }

    return firstAndLast;
  }

  private BstNode[] flattenRecursiveInorder(BstNode cur) {
    BstNode[] firstAndLast = new BstNode[2];
    firstAndLast[0] = firstAndLast[1] = cur;
    BstNode[] next;

    if (cur.left != null) {
      next = flattenRecursiveInorder(cur.left);
      firstAndLast[0] = next[0];
      next[1].right = cur;
      cur.left = null;
    }

    if (cur.right != null) {
      next = flattenRecursiveInorder(cur.right);
      cur.right = next[0];
      firstAndLast[1] = next[1]; // update last
    }
    return firstAndLast;
  }

  /**
   * the recursion implementation of basic BST operations
   */
  public void insert(String key) {
    if (root == null)
      root = new BstNode(key);
    else
      insert(root, key);
  }

  private void insert(BstNode cur, String key) {
    if (cur.value >= Integer.parseInt(key)) {
      if (cur.left != null)
        insert(cur.left, key);
      else
        cur.left = new BstNode(key);
    } else {
      if (cur.right != null)
        insert(cur.right, key);
      else
        cur.right = new BstNode(key);
    }
  }

  /**
   * test if a tree is a bst
   */
  public boolean isBST() {
    return isBST(root) == null ? false : true;
    // return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  // top-down
  private boolean isBST(BstNode node, double max, double min) {
    if (node == null) return true;
    if (node.value > max || node.value < min) return false;
    return isBST(node.left, node.value - 1, min) && isBST(node.right, max, node.value + 1);
  }

  // bottom-up
  private double[] isBST(BstNode node) {
    if (node == null) return new double[] {Integer.MAX_VALUE, Integer.MIN_VALUE};

    double[] range = {node.value, node.value};
    double[] rangeLeft = isBST(node.left);
    if (rangeLeft == null || node.value <= rangeLeft[1]) return null;
    range[0] = Math.min(range[0], rangeLeft[0]);
    range[1] = Math.max(range[1], rangeLeft[1]);

    double[] rangeRight = isBST(node.right);
    if (rangeRight == null || node.value >= rangeRight[0]) return null;
    range[0] = Math.min(range[0], rangeRight[0]);
    range[1] = Math.max(range[1], rangeRight[1]);
    return range;
  }

  public BstNode leastCommonAncestor(BstNode n1, BstNode n2) {
    if (n1 == null) return n2;
    if (n2 == null) return n1;
    // System.out.println(n1+" "+n2);
    return leastCommonAncestor(root, n1, n2);
  }

  private BstNode leastCommonAncestor(BstNode cur, BstNode n1, BstNode n2) {
    if (cur == null || cur.value == n1.value || cur.value == n2.value) return cur;
    BstNode l = leastCommonAncestor(cur.left, n1, n2);
    BstNode r = leastCommonAncestor(cur.right, n1, n2);
    // System.out.println(cur+" "+l+" "+r);
    if (l != null && r != null) return cur;
    return l == null ? r : l;
  }

  public int longestOnesidePath() {
    int[] maxLen = new int[1];
    longestOnesidePath(new WrapperNode(root), null, 0, maxLen);
    return maxLen[0];
  }

  private void longestOnesidePath(WrapperNode cur, WrapperNode parent, int dir, int[] maxLen) {
    switch (dir) {
      case 0: // root node
        cur.direction = 0;
        cur.len = 1;
        break;
      case 1:
        cur.direction = 1;
        if (parent.direction >= 0) cur.len = parent.len + 1;
        else cur.len = 2;
        break;
      case -1:
        cur.direction = -1;
        if (parent.direction <= 0) cur.len = parent.len + 1;
        else cur.len = 2;
        break;
    }
    maxLen[0] = Math.max(maxLen[0], cur.len);
    if (cur.node.left != null) longestOnesidePath(new WrapperNode(cur.node.left), cur, 1, maxLen);
    if (cur.node.right != null)
      longestOnesidePath(new WrapperNode(cur.node.right), cur, -1, maxLen);
  }

  /*
   * maxDepth function
   */
  public int maxDepth() {
    return maxDepth(root, 0);
  }

  private int maxDepth(BstNode cur, int depth) {
    if (cur == null) return depth;
    int leftDepth = maxDepth(cur.left, depth + 1);
    int rightDepth = maxDepth(cur.right, depth + 1);
    return leftDepth >= rightDepth ? leftDepth : rightDepth;
  }

  /*
     *
     */
  public int maxDepthDifference() {
    if (root == null)
      return 0;
    else {
      int[] depthPair = new int[] {Integer.MAX_VALUE, 0};
      maxDepthDifference(root, 0, depthPair);
      return depthPair[1] - depthPair[0];
    }
  }

  private void maxDepthDifference(BstNode cur, int curDepth, int[] depthPair) {
    if (cur == null) return;
    if (cur.right == null && cur.left == null) {
      depthPair[0] = Math.min(depthPair[0], curDepth);
      depthPair[1] = Math.max(depthPair[1], curDepth);
    } else {
      maxDepthDifference(cur.left, curDepth + 1, depthPair);
      maxDepthDifference(cur.right, curDepth + 1, depthPair);
    }
  }

  /**
   * minValue function
   */
  public double minValue() {
    return minValue(root, Integer.MAX_VALUE);
  }

  private double minValue(BstNode cur, double value) {
    if (cur == null) return value;
    if (cur.value < value) {
      value = cur.value;
    }
    double leftMinValue = minValue(cur.left, value);
    double rightMinValue = minValue(cur.right, value);
    if (leftMinValue < value) {
      value = leftMinValue;
    }
    if (rightMinValue < value) {
      value = rightMinValue;
    }
    return value;
  }

  public void mirror() {
    mirror(root);
  }

  private BstNode mirror(BstNode cur) {
    if (cur != null) {
      BstNode tmp = mirror(cur.left);
      cur.left = mirror(cur.right);
      cur.right = tmp;
    }
    return cur;
  }

  /*
   * Preorder print
   */
  public void printTree(TraversalOrder order, Implementation impl) {
    switch (order) {
      case PREORDER:
        if (impl == Implementation.RECURSION)
          printTreePreorder(root);
        else
          printTreePreorderNonRecursive(root);
        break;
      case POSTORDER:
        if (impl == Implementation.RECURSION)
          printTreePostorder(root);
        else
          printTreePostorderNonRecursive(root);
        break;
      default:
        if (impl == Implementation.RECURSION)
          printTreeInorder(root);
        else
          printTreeInorderNonRecursive(root);
        break;
    }
    System.out.println();
  }

  private void printTreeInorder(BstNode cur) {
    if (cur == null) {
      return;
    }
    printTreeInorder(cur.left);
    System.out.println(cur);
    printTreeInorder(cur.right);
  }

  private void printTreeInorderNonRecursive(BstNode cur) {
    Stack<BstNode> stck = new Stack<BstNode>();

    while (!stck.empty() || cur != null) {
      if (cur != null) {
        stck.push(cur);
        cur = cur.left;
      } else {
        BstNode top = stck.pop();
        System.out.println(top);
        cur = top.right;
      }
    }
  }

  private void printTreePostorderNonRecursive(BstNode cur) {
    Stack<BstNode> stck = new Stack<BstNode>();
    if (cur == null) return;
    /*
     * We will need current pointer to the node we are currently traversing and the pointer to the
     * node we traversed previously.
     */
    BstNode prev = null;
    stck.push(cur);
    while (!stck.empty()) {
      cur = stck.peek();
      /* Traverse the tree down */
      if (prev == null || prev.left == cur || prev.right == cur) {
        if (cur.left != null)
          stck.push(cur.left);
        else {
          if (cur.right != null)
            stck.push(cur.right);
          else {
            // leaf node case
            System.out.println(stck.pop());
          }
        }
      } else {/* Traverse the tree up from the left */
        if (cur.left == prev) {
          if (cur.right != null)
            stck.push(cur.right);
          else {
            System.out.println(stck.pop());
          }
        } else {
          if (cur.right == prev) {
            System.out.println(stck.pop());
          }
        }
      }
      prev = cur;
    }
    return;
  }

  private void printTreePreorderNonRecursive(BstNode cur) {
    Stack<BstNode> stck = new Stack<BstNode>();
    stck.push(cur);
    while (!stck.empty()) {
      BstNode top = stck.pop();
      System.out.println(top);
      if (top.right != null) stck.push(top.right);
      if (top.left != null) stck.push(top.left);
    }
  }

  private void printTreePreorder(BstNode cur) {
    if (cur == null) {
      return;
    }
    System.out.println(cur);
    printTreePreorder(cur.left);
    printTreePreorder(cur.right);
  }

  public void printTreeDFS(String order) {
    switch (order) {
      case "pre":
        printTreePreorder(root);
        break;
      case "post":
        printTreePostorder(root);
        break;
      default:
        printTreeInorder(root);
    }
    System.out.println();
  }

  private void printTreePostorder(BstNode cur) {
    if (cur == null) {
      return;
    }
    printTreePostorder(cur.left);
    printTreePostorder(cur.right);
    System.out.println(cur);
  }

  public void printTreeInLevels() {
    printTreeInLevels(root);
    System.out.println();
  }

  private void printTreeInLevels(BstNode root) {
    if (root == null) return;
    LinkedList<BstNode> currentLvl = new LinkedList<BstNode>();
    LinkedList<BstNode> nextLvl = new LinkedList<BstNode>();
    nextLvl.add(root);
    while (!nextLvl.isEmpty()) {
      currentLvl = nextLvl;
      nextLvl = new LinkedList<BstNode>();
      while (!currentLvl.isEmpty()) {
        BstNode cur = currentLvl.pop();
        System.out.print(cur + "\t");
        if (cur.left != null) nextLvl.add(cur.left);
        if (cur.right != null) nextLvl.add(cur.right);
      }
      System.out.println();
    }
  }

  /*
   * printPaths
   */
  public void printPaths() {
    if (root == null) return;
    HashMap<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>();
    allPaths.put("", new ArrayList<String>()); // empty parent path for root
    printPaths(root, "", allPaths);
  }

  private void printPaths(BstNode cur, String parentKey, HashMap<String, ArrayList<String>> allPaths) {
    if (cur != null) {
      ArrayList<String> path = allPaths.get(parentKey);
      ArrayList<String> newPath = new ArrayList<String>();
      for (String key : path)
        newPath.add(key);
      newPath.add(cur.id);
      allPaths.put(cur.id, newPath);
      if (cur.right == null && cur.left == null) {
        for (String key : newPath)
          System.out.print(key);
        System.out.println();
      } else {
        printPaths(cur.left, cur.id, allPaths);
        printPaths(cur.right, cur.id, allPaths);
      }
    }
  }

  /*
   * test if two trees are identical
   */
  public boolean sameTree(BstNode otherTreeRoot) {
    return sameTree(root, otherTreeRoot);
  }

  private boolean sameTree(BstNode one, BstNode another) {
    if (one == null && another == null)
      return true;
    else {
      if (one == null || another == null || one.value != another.value)
        return false;
      else
        return sameTree(one.left, another.left) && sameTree(one.right, another.right);
    }
  }

  public BstNode search(BstNode cur, String key) {
    if (cur == null) return cur;
    if (cur.id.compareTo(key) > 0)
      return search(cur.left, key);
    else {
      if (cur.id.compareTo(key) < 0)
        return search(cur.right, key);
      else
        return cur;
    }
  }

  /*
   * size function
   */
  public int size() {
    return size(root, 0);
  }

  private int size(BstNode cur, int num) {
    if (cur == null) return num;
    return size(cur.left, num) + 1 + size(cur.right, num);
  }

  /*
   * """ For the key values 1...numKeys, how many structurally unique binary search trees are
   * possible that store those keys? print all these trees in the inorder Strategy: consider that
   * each value could be the root. Recursively find the size of the left and right subtrees. """
   */
  public void printAllBinaryTreesPermutation(int n) {
    ArrayList<BstNode> trees = BuildAllBinaryTreesPermutation(1, n);
    for (BstNode t : trees) {
      printTreeInLevels(t);
      System.out.println();
    }
  }

  public void printPrettyTree() {
    // System.out.println(String.format("%-3d%5d", 12, 3));

    HashMap<BstNode, Integer> spaceAhead = new HashMap<BstNode, Integer>();
    int[] cumSum = new int[] {0};
    BstNode root = this.root;
    if (root == null) {
      System.err.println("Empty root node");
      return;
    }
    calSpaceAhead(root, spaceAhead, cumSum); // traverse the tree inorderly to calculate the spaces
                                             // before each node
    int size = cumSum[0] * 2;

    // represent a tree using two-dimensional char array
    char[][] prettyTree = new char[size][size];
    for (int i = 0; i < size; i++)
      Arrays.fill(prettyTree[i], ' ');

    // populate the char array by travesing the tree by level;
    int row = 0;
    // printTree("pre");
    HashMap<BstNode, Integer> rows = new HashMap<BstNode, Integer>(); // stores the row position of
                                                                      // nodes
    rows.put(root, 0); // root is at 0th row
    while (root != null) {
      BstNode nextLvl = null, prev = null;
      for (; root != null; root = root.next) {
        if (nextLvl == null) nextLvl = root.left != null ? root.left : root.right;
        int space = spaceAhead.get(root);
        row = rows.get(root);
        putNumberInLine(prettyTree, row, space, root.value);
        if (root.left != null) {
          if (prev != null) prev.next = root.left;
          prev = root.left;
          // generating the slashes
          int r = row + 1;
          for (int i = space - 1; i >= spaceAhead.get(root.left) && i >= 0; i--)
            prettyTree[r++][i] = '/';
          rows.put(root.left, r);
        }
        if (root.right != null) {
          if (prev != null) prev.next = root.right;
          prev = root.right;
          // generating the slashes
          int r = row + 1;
          for (int i = space + 1; i <= spaceAhead.get(root.right) && i < size; i++)
            prettyTree[r++][i] = '\\';
          rows.put(root.right, r);
        }

      }
      root = nextLvl;
      // row++;
    }

    // //print tree
    // System.out.println(size);
    for (int i = 0; i < row + 1; i++) {
      for (int j = 0; j < prettyTree[0].length; j++) {
        System.out.print(prettyTree[i][j]);
      }
      System.out.println();
    }
  }

  public boolean subtree(BinarySearchTree s) {
    if (s == null) return true;
    return subtree(root, s.root);
  }

  private boolean subtree(BstNode t1, BstNode t2) {
    if (t2 == null) return true;
    if (t1 == null) return false;

    if (t1.value == t2.value) { // current node of t1 matches t2's root
      // System.out.println(t1+" " + t2+" "+t1.right+" "+t2.right );
      return subtree(t1.left, t2.left) && subtree(t1.right, t2.right);
    }
    // System.out.println(t1+" " + t2 );
    return subtree(t1.left, t2) || subtree(t1.right, t2);
  }

  // find the successor of a node in inorder travesal
  public BstNode successorInorder(int value) {
    if (root == null) return root;
    return successorInorder(root, value, new int[] {0});
  }

  private BstNode successorInorder(BstNode cur, int value, int[] isFound) {
    // System.out.println(Arrays.toString(isFound)+" "+cur);
    if (cur == null) return cur;
    BstNode left = successorInorder(cur.left, value, isFound);
    if (left != null) return left;
    if (isFound[0] == 1) return cur; // next Node;
    if (cur.value == value) isFound[0] = 1;
    return successorInorder(cur.right, value, isFound);
  }

  private BstNode balanceTree(BstNode root) {
    ArrayList<BstNode> nodes = new ArrayList<BstNode>();
    while (root != null) {
      nodes.add(root);
      root = root.right;
    }
    return balanceTreeRecursive(nodes);
  }

  private BstNode balanceTreeRecursive(List<BstNode> nodes) {
    int n = nodes.size();
    if (n == 0) return null;
    int mid = n / 2;
    BstNode root = nodes.get(mid);
    root.left = balanceTreeRecursive(nodes.subList(0, mid));
    root.right = balanceTreeRecursive(nodes.subList(mid + 1, n));
    return root;
  }

  // traverse inorderly, return the total width of spaces of the tree
  private void calSpaceAhead(BstNode root, HashMap<BstNode, Integer> spaceAhead, int[] cumSum) {
    if (root == null) return;
    calSpaceAhead(root.left, spaceAhead, cumSum);
    // System.out.println(root.value+" : "+cumSum[0]);
    spaceAhead.put(root, cumSum[0]);
    cumSum[0] += numberLength(root.value);
    calSpaceAhead(root.right, spaceAhead, cumSum);
  }

  private int numberLength(double num) {
    return String.valueOf(num).length();
  }

  private BinarySearchTree merge(BinarySearchTree t2) {
    return new BinarySearchTree(balanceTree(merge(flattenRecursiveInorder(root)[0],
        flattenRecursiveInorder(t2.root)[0])));
  }

  // merge sort
  private BstNode merge(BstNode l1, BstNode l2) {
    BstNode head = l2, l2Prev = null;
    while (l1 != null && l2 != null) {
      if (l1.value >= l2.value) {
        l2Prev = l2;
        l2 = l2.right;
      } else {
        if (l2Prev != null) {
          l2Prev.right = l1;
          l1 = l1.right; // advance l1
          l2Prev.right.right = l2;
          l2Prev = l2Prev.right; // advance l2Prev
        } else {
          l2Prev = l1; // advance l2Prev
          head = l2Prev; // change head
          l1 = l1.right; // advance l1
          l2Prev.right = l2;
        }
      }
    }
    if (l2 == null && l1 != null) {
      if (l2Prev != null)
        l2Prev.right = l1; // copy rest of l1 to l2
      else
        head = l1;
    }
    return head;
  }

  private void putNumberInLine(char[][] prettyTree, int row, int startCol, double value) {
    String v = String.valueOf(value);
    for (int j = 0; j < v.length(); j++)
      prettyTree[row][j + startCol] = v.charAt(j);
  }

  public static void main(String[] args) {
    new BinarySearchTree().main();
  }

  public void main() {
      /**
       *                        5
       *                      /   \
       *                     3     22
       *                    / \    /
       *                   1   7 20
       *                         /
       *                        6
       *                         \
       *                          10
       */

    BinarySearchTree bst = new BinarySearchTree();
    bst.insert("5");
    bst.insert("22");
    bst.insert("3");
    bst.insert("1");
    bst.insert("7");
    bst.insert("20");
    bst.insert("6");
    bst.insert("10");
    // System.out.println(bst.size()+" , "+bst.maxDepth()+" , "+bst.minValue()+" , "+bst.maxDepthDifference());
    bst.mirror();
    bst.printPrettyTree();

    System.out.println("longest one side path length is : " + bst.longestOnesidePath() );

    if (true) return;

    // bst.printTreeInLevels();
    Implementation impl = Implementation.ITERATIVE;
    bst.printTree(TraversalOrder.POSTORDER, impl);
    bst.printTree(TraversalOrder.PREORDER, impl);
    bst.printTree(TraversalOrder.INORDER, impl);
    bst.printPrettyTree();
    // bst.printPaths();

    // System.out.println(bst.countTree(7));
    // bst.printAllBinaryTreesPermutation(4);

    // System.out.print(bst.firstCommonAncestor(new Node("20"), new Node("22") ));

    // bst.printPrettyTree();
    System.out.println(bst.successorInorder(5));

    // test subtree()
    BinarySearchTree bst1 = new BinarySearchTree();
    bst1.insert("22");
    bst1.insert("7");
    bst1.insert("20");
    bst1.insert("6");
    // bst1.printPrettyTree();

    // System.out.print(bst.subtree(bst1));

    // test merge()
    BinarySearchTree bst2 = new BinarySearchTree();
    bst2.insert("8");
    bst2.insert("13");
    bst2.insert("9");
    bst2.insert("2");
    // bst2.printPrettyTree();

    BinarySearchTree merged = bst.merge(bst2);
    // merged.printPrettyTree();

    merged.root = merged.flattenRecursiveInorder(merged.root)[0];
    // merged.flattenRecursivePreorder(merged.root)[0];

    // merged.printTree(TRAVERSAL_ORDER.POSTORDER);
    // merged.printPrettyTree();

    // System.out.println(Integer.highestOneBit(1234) );
    // System.out.println(Arrays.toString(Integer.toBinaryString(1234 & 0xaaaaaaa).toCharArray()) );
  }

  public static class BstNode extends Node {
    String id;
    double value;
    public BstNode left;
    public BstNode right;
    BstNode next;

    public BstNode(Node node) {
      id = node.id;
      value = node.value;
    }

    BstNode(BstNode copy) {
      this.id = copy.id;
      this.value = copy.value;
      this.left = copy.left;
      this.right = copy.right;
    }

    BstNode(String key, int value) {
      this.id = key;
      this.value = value;
      left = null;
      right = null;
      next = null;
    }

    BstNode(String key) {
      this(key, Integer.parseInt(key));
    }

    @Override
    public String toString() {
      return "(" + id + "," + value + ")";
    }
  }

  private static class WrapperNode {
    private BstNode node;
    private int direction;
    private int len;

    public WrapperNode(BstNode node) {
      this.node = node;
    }
  }
}
