package com.shuoma.ds.graph.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BST {
  public static void main(String[] args) {
    /**
     *                    5
     *                   / \
     *                  3   22
     *                 /   / \
     *                1   7   25
     *                   / \
     *                  6   20
     *                     /
     *                    10
     *                      \
     *                      13
     */

    int[] nodes = {5, 22, 3, 1, 7, 20, 6, 10, 25, 13};
    BinarySearchTree bst = new BinarySearchTree(nodes);
    // System.out.println(bst.size()+" , "+bst.maxDepth()+" , "+bst.minValue()+" , "+bst.maxDepthDifference());
    // bst.mirror();
    //bst.printTreeByColumn();

    System.out.println(
        "dis btw: " + bst.leastCommonAncestor(new BSTNode("3"), new BSTNode("25")).dis);

    bst.printPrettyTree();
    bst.printTreeInLevels();
    bst.delete("7");
    bst.printTreeInLevels();
    bst.printPrettyTree();
    bst.printTreeInorderNonRecursiveWithoutStack();
    // System.out.println("longest one side path length is : " + bst.longestOnesidePath() );

//    if (true)
//      return;

    // bst.printTreeInLevels();
    bst.printPrettyTree();
    // bst.printPaths();

    // System.out.println(bst.countTree(7));
    // bst.printAllBinaryTreesPermutation(4);



    // bst.printPrettyTreeOld();
    System.out.println(bst.successorInorder(5));

    // test subtree()
    nodes = new int[] {22, 7, 20, 6};
    BinarySearchTree bst1 = new BinarySearchTree(nodes);
    // bst1.printPrettyTreeOld();

    // System.out.print(bst.subtree(bst1));

    // test merge()
    nodes = new int[] {8, 13, 9, 2};
    BinarySearchTree bst2 = new BinarySearchTree(nodes);
    bst2.printPrettyTree();

    BinarySearchTree merged = bst.merge(bst2);
    merged.printPrettyTree();

    merged.root = merged.flattenRecursiveInorder(merged.root)[0];
    // merged.flattenRecursivePreorder(merged.root)[0];

    // merged.printTree(TRAVERSAL_ORDER.POSTORDER);
    // merged.printPrettyTree();

    // System.out.println(Integer.highestOneBit(1234) );
    // System.out.println(Arrays.toString(Integer.toBinaryString(1234 & 0xaaaaaaa).toCharArray()) );
  }

  public static class BinarySearchTree {
    private BSTNode root;

    public BinarySearchTree(int[] keys) {
      insert(keys);
    }

    public BinarySearchTree(BSTNode root) {
      this.root = root;
    }

    private ArrayList<BSTNode> buildAllBinaryTreesPermutation(int min, int max) {
      ArrayList<BSTNode> ret = new ArrayList<BSTNode>();
      if (min > max) {
        ret.add(null);
      } else {
        for (int i = min; i <= max; i++) {
          BSTNode root = new BSTNode(String.valueOf(i), i);
          ArrayList<BSTNode> left_trees = buildAllBinaryTreesPermutation(min, i - 1);
          ArrayList<BSTNode> right_trees = buildAllBinaryTreesPermutation(i + 1, max);
          for (BSTNode l : left_trees)
            for (BSTNode r : right_trees) {
              BSTNode newTree = new BSTNode(root);
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
      if (N <= 1)
        return 1;
      int sum = 0;
      for (int i = 1; i <= N; i++) {
        sum += countTree(i - 1) * countTree(N - i);
      }
      return sum;
    }

    /** Return the root after the deletion */
    public BSTNode delete(String id) {
      return delete(root, id);
    }

    public BSTNode delete(BSTNode node) {
      return delete(root, node.id);
    }

    /** Cannot directly call find() since we need to remember parent */
    private BSTNode delete(BSTNode cur, String id) {
      if (cur == null)
        return null;
      int compare = Integer.parseInt(id) - Integer.parseInt(cur.id);
      if (compare == 0) {
        if (cur.left == null)
          return cur.right;
        if (cur.right == null)
          return cur.left;
        // get Min of right tree
        BSTNode successor = cur.right, parent = cur;
        while (successor.left != null) {
          parent = successor;
          successor = successor.left;
        }
        // replace cur by successor
        cur.value = successor.value;
        cur.id = successor.id;
        // remove successor from right tree
        if (parent.left == successor)
          parent.left = successor.right;
        else
          parent.right = successor.right;
        // alternative approach :
        //cur.right = delete(cur.right, successor.id);
      } else {
        if (compare > 0)
          cur.right = delete(cur.right, id);
        else
          cur.left = delete(cur.left, id);
      }
      return cur;
    }

    public BSTNode find(String key) {
      return find(root, key);
    }

    public BSTNode find(BSTNode node) {
      return find(root, node.id);
    }

    private BSTNode find(BSTNode cur, String id) {
      if (cur == null)
        return null;
      int compare = Integer.parseInt(id) - Integer.parseInt(cur.id);
      if (compare == 0)
        return cur;
      else
        return compare > 0 ? find(cur.right, id) : find(cur.left, id);
    }

    private BSTNode[] flattenRecursivePreorder(BSTNode cur) {
      BSTNode[] firstAndLast = new BSTNode[2];
      firstAndLast[0] = firstAndLast[1] = cur;
      BSTNode[] next;

      BSTNode firstOfRightSubtree = null;
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
        if (firstAndLast[1].equals(cur))
          firstAndLast[1] = next[1]; // update last
        // firstAndLast[0]=cur; //update first
      }

      return firstAndLast;
    }

    private BSTNode[] flattenRecursiveInorder(BSTNode cur) {
      BSTNode[] firstAndLast = new BSTNode[2];
      firstAndLast[0] = firstAndLast[1] = cur;
      BSTNode[] next;

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

    public BSTNode getRoot() {
      return root;
    }

    public void insert(int[] keys) {
      for (int key : keys)
        root = insert(root, String.valueOf(key));
    }

    /** the recursion implementation of basic BST operations. */
    protected BSTNode insert(BSTNode cur, String key) {
      if (cur == null)
        return new BSTNode(key);
      if (cur.value >= Integer.parseInt(key))
        cur.left = insert(cur.left, key);
      else
        cur.right = insert(cur.right, key);
      return cur;
    }

    /**
     * test if a tree is a bst
     */
    public boolean isBST() {
      return isBST(root) == null ? false : true;
      // return isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    // top-down
    private boolean isBST(BSTNode node, double max, double min) {
      if (node == null)
        return true;
      if (node.value > max || node.value < min)
        return false;
      return isBST(node.left, node.value - 1, min) && isBST(node.right, max, node.value + 1);
    }

    // bottom-up
    private double[] isBST(BSTNode node) {
      if (node == null)
        return new double[] {Integer.MAX_VALUE, Integer.MIN_VALUE};

      double[] range = {node.value, node.value};
      double[] rangeLeft = isBST(node.left);
      if (rangeLeft == null || node.value <= rangeLeft[1])
        return null;
      range[0] = Math.min(range[0], rangeLeft[0]);
      range[1] = Math.max(range[1], rangeLeft[1]);

      double[] rangeRight = isBST(node.right);
      if (rangeRight == null || node.value >= rangeRight[0])
        return null;
      range[0] = Math.min(range[0], rangeRight[0]);
      range[1] = Math.max(range[1], rangeRight[1]);
      return range;
    }

    public BSTNodeDisWrapper leastCommonAncestor(BSTNode n1, BSTNode n2) {
      if (n1 == null)
        return new BSTNodeDisWrapper(n2, 0);
      if (n2 == null)
        return new BSTNodeDisWrapper(n1, 0);
      // System.out.println(n1+" "+n2);
      return leastCommonAncestor(root, n1, n2);
    }

    private BSTNodeDisWrapper leastCommonAncestor(BSTNode cur, BSTNode n1, BSTNode n2) {
      if (cur == null) return null;
      if (cur.value == n1.value || cur.value == n2.value) return new BSTNodeDisWrapper(cur, 0);

      BSTNodeDisWrapper l = leastCommonAncestor(cur.left, n1, n2);
      BSTNodeDisWrapper r = leastCommonAncestor(cur.right, n1, n2);
      if (l != null && r != null)
        return new BSTNodeDisWrapper(cur, l.dis + r.dis + 2);

      if (l != null) {
        l.dis++;
        return l;
      }
      if (r != null) r.dis++;
      return r;
    }

    public int longestOnesidePath() {
      int[] maxLen = new int[1];
      longestOnesidePath(new DirectedBSTNode(root), null, 0, maxLen);
      return maxLen[0];
    }

    private void longestOnesidePath(DirectedBSTNode cur, DirectedBSTNode parent, int dir, int[] maxLen) {
      switch (dir) {
        case 0: // root node
          cur.direction = 0;
          cur.len = 1;
          break;
        case 1:
          cur.direction = 1;
          if (parent.direction >= 0)
            cur.len = parent.len + 1;
          else
            cur.len = 2;
          break;
        case -1:
          cur.direction = -1;
          if (parent.direction <= 0)
            cur.len = parent.len + 1;
          else
            cur.len = 2;
          break;
      }
      maxLen[0] = Math.max(maxLen[0], cur.len);
      if (cur.left != null)
        longestOnesidePath(new DirectedBSTNode(cur.left), cur, 1, maxLen);
      if (cur.right != null)
        longestOnesidePath(new DirectedBSTNode(cur.right), cur, -1, maxLen);
    }

    /*
     * maxDepth function
     */
    public int maxDepth() {
      return maxDepth(root, 0);
    }

    private int maxDepth(BSTNode cur, int depth) {
      if (cur == null)
        return depth;
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

    private void maxDepthDifference(BSTNode cur, int curDepth, int[] depthPair) {
      if (cur == null)
        return;
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

    private double minValue(BSTNode cur, double value) {
      if (cur == null)
        return value;
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

    private BSTNode mirror(BSTNode cur) {
      if (cur != null) {
        BSTNode tmp = mirror(cur.left);
        cur.left = mirror(cur.right);
        cur.right = tmp;
      }
      return cur;
    }

    public void printTree(TraversalMethod method) {
      switch (method) {
        case INORDER_ITERATIVE_WITH_STACK:
          printTreeInorderNonRecursive(root);
          break;
        case INORDER_ITERATIVE_WITHOUT_STACK:
          printTreeInorderNonRecursiveWithoutStack();
          break;
        case INORDER_RECUSRIVE:
          printTreeInorder(root);
        case PREORDER_ITERATIVE_WITH_STACK:
          printTreePreorderNonRecursive(root);
          break;
        case PREORDER_RECUSRIVE:
            printTreePreorder(root);
          break;
        case POSTORDER_ITERATIVE_WITH_STACK:
          printTreePostorderNonRecursive(root);
          break;
        case POSTORDER_RECUSRIVE:
            printTreePostorder(root);
          break;
        default:
          throw new IllegalArgumentException(method + " does not exists");
      }
      System.out.println();
    }

    public void printTreeByColumn() {
      Map<Integer, List<String>> columns = new HashMap<>();
      printTreeByColumn(root, columns, 0);
      List<Integer> sortedColumnIdx = new ArrayList(columns.keySet());
      Collections.sort(sortedColumnIdx);
      for (int idx : sortedColumnIdx) {
        System.out.println(columns.get(idx));
      }
    }

    private void printTreeByColumn(BSTNode cur, Map<Integer, List<String>> columns, int columnIdx) {
      if (cur == null) {
        return;
      }

      printTreeByColumn(cur.left, columns, columnIdx - 1);

      List<String> column = columns.get(columnIdx);
      if (column == null) {
        column = new LinkedList<>();
      }
      column.add(cur.id);
      columns.put(columnIdx, column);

      printTreeByColumn(cur.right, columns, columnIdx + 1);
    }

    private void printTreeInorder(BSTNode cur) {
      if (cur == null) {
        return;
      }
      printTreeInorder(cur.left);
      System.out.println(cur);
      printTreeInorder(cur.right);
    }

    private void printTreeInorderNonRecursive(BSTNode cur) {
      Stack<BSTNode> stck = new Stack<>();

      while (!stck.empty() || cur != null) {
        if (cur != null) {
          stck.push(cur);
          cur = cur.left;
        } else {
          BSTNode top = stck.pop();
          System.out.println(top);
          cur = top.right;
        }
      }
    }

    public void printTreeInorderNonRecursiveWithoutStack() {
      printTreeInorderNonRecursiveWithoutStack(root.left, root);
    }

    private void printTreeInorderNonRecursiveWithoutStack(BSTNode current, BSTNode parent) {
      while (current != null) {
        if (parent != null) {
          parent.left = current.right;
          current.right = parent;
        }
        if (current.left != null) {
          parent = current;
          current = current.left;
        } else {
          System.out.println(current.value);
          current = current.right;
          parent = null;
        }
      }
    }

    private void printTreePostorderNonRecursive(BSTNode cur) {
      Stack<BSTNode> stck = new Stack<>();
      if (cur == null)
        return;
      /*
       * We will need current pointer to the node we are currently traversing and the pointer to the
       * node we traversed previously.
       */
      BSTNode prev = null;
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

    private void printTreePreorderNonRecursive(BSTNode cur) {
      Stack<BSTNode> stck = new Stack<>();
      stck.push(cur);
      while (!stck.empty()) {
        BSTNode top = stck.pop();
        System.out.println(top);
        if (top.right != null)
          stck.push(top.right);
        if (top.left != null)
          stck.push(top.left);
      }
    }

    private void printTreePreorder(BSTNode cur) {
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

    private void printTreePostorder(BSTNode cur) {
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

    private void printTreeInLevels(BSTNode root) {
      if (root == null)
        return;
      LinkedList<BSTNode> currentLvl;
      LinkedList<BSTNode> nextLvl = new LinkedList<>();
      nextLvl.add(root);
      while (!nextLvl.isEmpty()) {
        currentLvl = nextLvl;
        nextLvl = new LinkedList<>();
        while (!currentLvl.isEmpty()) {
          BSTNode cur = currentLvl.pop();
          System.out.print(cur + "\t");
          if (cur.left != null)
            nextLvl.add(cur.left);
          if (cur.right != null)
            nextLvl.add(cur.right);
        }
        System.out.println();
      }
    }

    /*
     * printPaths
     */
    public void printPaths() {
      if (root == null)
        return;
      HashMap<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>();
      allPaths.put("", new ArrayList<String>()); // empty parent path for root
      printPaths(root, "", allPaths);
    }

    private void printPaths(BSTNode cur, String parentKey, HashMap<String, ArrayList<String>> allPaths) {
      if (cur != null) {
        ArrayList<String> path = allPaths.get(parentKey);
        ArrayList<String> newPath = new ArrayList<>();
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
    public boolean sameTree(BSTNode otherTreeRoot) {
      return sameTree(root, otherTreeRoot);
    }

    private boolean sameTree(BSTNode one, BSTNode another) {
      if (one == null && another == null)
        return true;
      else {
        if (one == null || another == null || one.value != another.value)
          return false;
        else
          return sameTree(one.left, another.left) && sameTree(one.right, another.right);
      }
    }

    public BSTNode search(BSTNode cur, String key) {
      if (cur == null)
        return cur;
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

    private int size(BSTNode cur, int num) {
      if (cur == null)
        return num;
      return size(cur.left, num) + 1 + size(cur.right, num);
    }

    /*
     * """ For the key values 1...numKeys, how many structurally unique binary search trees are
     * possible that store those keys? print all these trees in the inorder Strategy: consider that
     * each value could be the root. Recursively find the size of the left and right subtrees. """
     */
    public void printAllBinaryTreesPermutation(int n) {
      ArrayList<BSTNode> trees = buildAllBinaryTreesPermutation(1, n);
      for (BSTNode t : trees) {
        printTreeInLevels(t);
        System.out.println();
      }
    }

    public void printPrettyTree() {
      HashMap<Integer, List<TreeBoardCell>> columns = new HashMap<>();
      int[] colIdxRange = new int[] {0, 0};
      int[] rowIdxRange = new int[] {0, 0};
      printPrettyTree(null, root, columns, 0, 0, colIdxRange, rowIdxRange, 1);
      TreeBoard tb = new TreeBoard(columns, colIdxRange, rowIdxRange[1] + 1);
      System.out.println(tb.toString());
    }

    private void printPrettyTree(BSTNode parent, BSTNode cur, HashMap<Integer,
        List<TreeBoardCell>> columns, int curCol, int curRow, int[] colIdxRange, int[] rowIdxRange,
        int nLineBetweenLevels) {
      if (cur.left != null) {
        for (int i = 1; i <= nLineBetweenLevels; i++) {
          upsert(columns, curRow + i, curCol - i, "/", false);
        }
        printPrettyTree(cur, cur.left, columns, curCol - nLineBetweenLevels - 1,
            curRow + nLineBetweenLevels + 1, colIdxRange, rowIdxRange, nLineBetweenLevels);
      }

      colIdxRange[0] = Math.min(colIdxRange[0], curCol);
      colIdxRange[1] = Math.max(colIdxRange[1], curCol);
      rowIdxRange[1] = Math.max(rowIdxRange[1], curRow);
      boolean isRightChild = parent != null && parent.right == cur;
      upsert(columns, curRow, isRightChild ? curCol - 1 : curCol, cur.id, isRightChild);

      if (cur.right != null) {
        for (int i = 1; i <= nLineBetweenLevels; i++) {
          upsert(columns, curRow + i, curCol + i, "\\", true);
        }
        printPrettyTree(cur, cur.right, columns, curCol + nLineBetweenLevels + 1,
            curRow + nLineBetweenLevels + 1, colIdxRange, rowIdxRange, nLineBetweenLevels);
      }
    }

    private void upsert(HashMap<Integer, List<TreeBoardCell>> columns,
        int curRow, int curCol, String val, boolean isRightChild) {
      List<TreeBoardCell> column = columns.get(curCol);
      if (column == null) {
        column = new LinkedList<>();
      }
      column.add(new TreeBoardCell(isRightChild, curRow, curCol, val));
      columns.put(curCol, column);
    }

    public void printPrettyTreeOld() {
      // System.out.println(String.format("%-3d%5d", 12, 3));

      HashMap<BSTNode, Integer> spaceAhead = new HashMap<>();
      int[] cumSum = new int[] {0};
      BSTNode root = this.root;
      if (root == null) {
        System.err.println("Empty root node");
        return;
      }
      // traverse the tree inorderly to calculate the spaces before each node
      calSpaceAhead(root, spaceAhead, cumSum);
      int size = cumSum[0] * 2;

      // represent a tree using two-dimensional char array
      char[][] prettyTree = new char[size][size];
      for (int i = 0; i < size; i++)
        Arrays.fill(prettyTree[i], ' ');

      // populate the char array by travesing the tree by level;
      int row = 0;
      // stores the row position of nodeMap
      HashMap<BSTNode, Integer> rows = new HashMap<>();
      rows.put(root, 0); // root is at 0th row
      while (root != null) {
        BSTNode nextLvl = null, prev = null;
        for (; root != null; root = root.next) {
          if (nextLvl == null)
            nextLvl = root.left != null ? root.left : root.right;
          int space = spaceAhead.get(root);
          row = rows.get(root);
          putNumberInLine(prettyTree, row, space, root.id);
          if (root.left != null) {
            if (prev != null)
              prev.next = root.left;
            prev = root.left;
            // generating the slashes
            int r = row + 1;
            for (int i = space - 1; i >= spaceAhead.get(root.left) && i >= 0; i--)
              prettyTree[r++][i] = '/';
            rows.put(root.left, r);
          }
          if (root.right != null) {
            if (prev != null)
              prev.next = root.right;
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
      if (s == null)
        return true;
      return subtree(root, s.root);
    }

    private boolean subtree(BSTNode t1, BSTNode t2) {
      if (t2 == null)
        return true;
      if (t1 == null)
        return false;

      if (t1.value == t2.value) { // current node of t1 matches t2's root
        // System.out.println(t1+" " + t2+" "+t1.right+" "+t2.right );
        return subtree(t1.left, t2.left) && subtree(t1.right, t2.right);
      }
      // System.out.println(t1+" " + t2 );
      return subtree(t1.left, t2) || subtree(t1.right, t2);
    }

    // find the successor of a node in inorder travesal
    public BSTNode successorInorder(int value) {
      if (root == null)
        return root;
      return successorInorder(root, value, new int[] {0});
    }

    private BSTNode successorInorder(BSTNode cur, int value, int[] isFound) {
      // System.out.println(Arrays.toString(isFound)+" "+cur);
      if (cur == null)
        return cur;
      BSTNode left = successorInorder(cur.left, value, isFound);
      if (left != null)
        return left;
      if (isFound[0] == 1)
        return cur; // next Node;
      if (cur.value == value)
        isFound[0] = 1;
      return successorInorder(cur.right, value, isFound);
    }

    private BSTNode balanceTree(BSTNode root) {
      ArrayList<BSTNode> nodes = new ArrayList<>();
      while (root != null) {
        nodes.add(root);
        root = root.right;
      }
      return balanceTreeRecursive(nodes);
    }

    private BSTNode balanceTreeRecursive(List<BSTNode> nodes) {
      int n = nodes.size();
      if (n == 0)
        return null;
      int mid = n / 2;
      BSTNode root = nodes.get(mid);
      root.left = balanceTreeRecursive(nodes.subList(0, mid));
      root.right = balanceTreeRecursive(nodes.subList(mid + 1, n));
      return root;
    }

    // traverse inorderly, return the total width of spaces of the tree
    private void calSpaceAhead(BSTNode root, HashMap<BSTNode, Integer> spaceAhead, int[] cumSum) {
      if (root == null)
        return;
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
      return new BinarySearchTree(balanceTree(
          merge(flattenRecursiveInorder(root)[0], flattenRecursiveInorder(t2.root)[0])));
    }

    // merge sort
    private BSTNode merge(BSTNode l1, BSTNode l2) {
      BSTNode head = l2, l2Prev = null;
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

    private void putNumberInLine(char[][] prettyTree, int row, int startCol, String value) {
      String v = String.valueOf(value);
      for (int j = 0; j < v.length(); j++)
        prettyTree[row][j + startCol] = value.charAt(0);
    }
  }

  private static class BSTNodeDisWrapper {
    BSTNode node;
    int dis;

    public BSTNodeDisWrapper(BSTNode node, int dis) {
      this.node = node;
      this.dis = dis;
    }
  }

  private static class DirectedBSTNode extends BSTNode {
    private int direction;
    private int len;

    public DirectedBSTNode(BSTNode node) {
      super(node);
    }
  }

  private static class TreeBoard {
    List<List<TreeBoardCell>> cells;

    public TreeBoard(HashMap<Integer, List<TreeBoardCell>> columns, int[] columnIdxRange, int nRows) {
      cells = new LinkedList<>();
      for (int i = 0; i < nRows; i++) cells.add(new LinkedList<TreeBoardCell>());

      List<Integer> sortedColumnIdx = new ArrayList(columns.keySet());
      Collections.sort(sortedColumnIdx);
      for (int idx : sortedColumnIdx) {
        for (TreeBoardCell cell : columns.get(idx)) {
          cell.colIdx -= columnIdxRange[0];
          cells.get(cell.rowIdx).add(cell);
        }
      }
    }

    @Override
    public String toString(){
      StringBuilder sb = new StringBuilder();
      for (List<TreeBoardCell> row : cells) {
        TreeBoardCell cur, prev = null;
        for (int i = 0; i < row.size(); i++) {
          cur = row.get(i);
          int diffSpaces;
          if (prev != null) {
            diffSpaces = cur.colIdx - (prev.colIdx + prev.val.length());
          } else {
            diffSpaces = cur.colIdx;
          }
          for (int j = 0; j < diffSpaces; j++) sb.append(" ");
          sb.append(cur.toString());
          prev = cur;
        }
        sb.append("\n");
      }
      return sb.toString();
    }
  }

  private static class TreeBoardCell {
    boolean isRightChild;
    int colIdx;
    int rowIdx;
    String val;

    public TreeBoardCell(boolean isRightChild, int rowIdx, int colIdx, String val) {
      this.isRightChild = isRightChild;
      this.colIdx = colIdx;
      this.rowIdx = rowIdx;
      this.val = val;
    }

    @Override
    public String toString(){
      return val;
    }
  }
}
