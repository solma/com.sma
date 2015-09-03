package com.shuoma.ds.graph.tree;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.shuoma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static java.lang.Integer.parseInt;
import static java.util.Collections.reverse;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Tag(dss = {BinarySearchTree, BinaryTree})
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
    BSTNode bst = insert(nodes);

    printTreeInorderMorris(bst);
    System.out.println();
    System.out.println(size(bst));
    System.out.println(maxDistanceBtwLeaves(bst));
    //System.out.println(bst.findAll("5"));
    if (true) {
      //return;
    }

    prettyPrint(bst);
    System.out.println();
    printTreeByRows(bst);
    System.out.println();
    printTreeByColumn(bst);
    System.out.println();

    // System.out.println(bst.size()+" , "+bst.maxDepth()+" , "+bst.minValue()+" , "+bst.maxDepthDifference());
    // bst.mirror();

    int x = 7;
    System.out.println(x + "th number in the tree (inorder) is: " + get(bst, x).toString());
    x = 25;
    System.out.println(
        x + " is " + rankOf(bst, String.valueOf(x)) + "th number in the tree (inorder).");
    prettyPrint(bst);
    x = 7;
    System.out.println(x + "th number in the tree (inorder) is: " + get(bst, x).toString());
    x = 25;
    System.out.println(x + " is " + rankOf(bst, String.valueOf(x)) + "th number in the tree (inorder).");


    nodes =new int[] {5, 6, 4, 2, 1, 3};
    bst = insert(nodes);
    prettyPrint(bst);
    flip(bst);
    prettyPrint(bst);

    // System.out.println("longest one side path length is : " + bst.longestOnesidePath() );

    // System.out.println(bst.countTree(7));
    // bst.printAllBinaryTreesPermutation(4);

    x = 3;
    System.out.println(x + " 's successor is: " + successorInorder(bst, x));

    // test subtree()
    nodes = new int[] {22, 7, 20, 6};
    BSTNode bst1 = insert(nodes);
    // bst1.printPrettyTreeOld();

    // System.out.print(bst.subtree(bst1));

    // test merge()
    nodes = new int[] {8, 13, 9, 2};
    BSTNode bst2 = insert(nodes);
    prettyPrint(bst2);

    BSTNode merged = merge(bst, bst2);
    prettyPrint(merged);

    System.out.println(flattenRecursiveInorder(merged)[0]);
    // merged.flattenRecursivePreorder(merged.root)[0];

    // merged.printTree(TRAVERSAL_ORDER.POSTORDER);

    // System.out.println(Integer.highestOneBit(1234) );
    // System.out.println(Arrays.toString(Integer.toBinaryString(1234 & 0xaaaaaaa).toCharArray()) );
  }

  public static ArrayList<BSTNode> buildAllBinaryTreesPermutation(int min, int max) {
    ArrayList<BSTNode> ret = new ArrayList<>();
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
  public static int countTree(int N) {
    return countTree(N, new HashMap<Integer, Integer>());
  }

  static int countTree(int N, Map<Integer, Integer> memory) {
    if (N <= 1) return 1;
    if (memory.containsKey(N)) return memory.get(N);
    int sum = 0;
    for (int i = 1; i <= N; i++) {
      sum += countTree(i - 1) * countTree(N - i);
    }
    memory.put(N, sum);
    return sum;
  }

  /** Cannot directly call find() since we need to remember parent */
  public static BSTNode delete(BSTNode cur, String id) {
    if (isNullOrEmpty(id)) return cur;
    if (cur == null) return null;
    int compare = parseInt(id) - parseInt(cur.id);
    // replace cur with its inorder successor, i.e. leftmost node in the right subtree
    if (compare == 0) {
      // no right subtree
      if (cur.right == null) return cur.left;

      // get leftmost node  of right tree
      BSTNode next = cur.right, parent = cur;
      while (next.left != null) {
        parent = next;
        next = next.left;
      }

      // replace cur by successor
      cur.value = next.value;
      cur.id = next.id;

      // handle right subtree of the successor
      if (!parent.id.equals(cur.id)) parent.left = next.right;
      // alternative approach :
      // cur.right = delete(cur.right, next.id);
    } else {
      if (compare > 0) cur.right = delete(cur.right, id);
      else cur.left = delete(cur.left, id);
    }
    cur.size--;
    return cur;
  }

  public static BSTNode find(BSTNode cur, String id) {
    if (cur == null)
      return null;
    int compare = parseInt(id) - parseInt(cur.id);
    if (compare == 0)
      return cur;
    else
      return compare > 0 ? find(cur.right, id) : find(cur.left, id);
  }

  public static List<BSTNode> findAll(BSTNode cur, String id) {
    List<BSTNode> ret = new LinkedList<>();
    if (cur == null) {
      return ret;
    }
    int compare = parseInt(id) - parseInt(cur.id);
    if (compare > 0) {
      return findAll(cur.right, id);
    }
    ret.addAll(findAll(cur.left, id));
    if (compare == 0) {
      ret.add(cur);
    }
    return ret;
  }

  public static BSTNode[] flattenRecursivePreorder(BSTNode cur) {
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

  public static BSTNode[] flattenRecursiveInorder(BSTNode cur) {
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


  /**
   * Given a binary tree where all the right node are either null or a leaf,
   * flip it upside down and turn it into a tree with left leaf node being root.
   * In the original tree, if a node has a right child, it also must have a left child.
   */
  public static BSTNode flip(BSTNode cur) {
    if (cur == null || cur.left == null) return cur;
    BSTNode left = cur.left;
    BSTNode newRoot = flip(left);
    left.right = cur;
    left.left = cur.right;
    cur.left = cur.right = null;
    return newRoot;
  }

  /** Get the nth node in preorder. */
  public static BSTNode get(BSTNode cur, int n) {
    if (cur == null) return null;

    int leftSize = cur.left == null ? 0 : cur.left.size;

    if (n == leftSize + 1) {
      return cur;
    }

    if (n <= leftSize) {
      return get(cur.left, n);
    }

    return get(cur.right, n - leftSize - 1);
  }

  /**
   * get height of the tree
   */
  public static int height(BSTNode cur) {
    if (cur == null) return 0;
    return Math.max(height(cur.left), height(cur.right)) + 1;
  }

  public static BSTNode insert(int key) {
    return insert(new int[]{key});
  }

  public static BSTNode insert(int[] keys) {
    BSTNode root = null;
    for (int key : keys) {
      root = insert(root, String.valueOf(key));
    }
    return root;
  }

  /** recursive insertion. */
  static BSTNode insert(BSTNode cur, String key) {
    if (cur == null) {
      BSTNode leaf = new BSTNode(key);
      leaf.size = 1;
      return leaf;
    }

    if (parseInt(cur.id) >= parseInt(key)) {
      cur.left = insert(cur.left, key);
    }
    else {
      cur.right = insert(cur.right, key);
    }
    cur.size++;
    return cur;
  }

  /** check if a tree is a bst. */
  public static boolean isBST(BSTNode cur) {
    //return isBSTBottomUp(root) != null;
    return isBSTTopDown(cur, Integer.MAX_VALUE, Integer.MIN_VALUE);
  }

  // top-down
  static boolean isBSTTopDown(BSTNode node, double max, double min) {
    if (node == null)
      return true;
    if (node.value > max || node.value < min)
      return false;
    return isBSTTopDown(node.left, node.value - 1, min) && isBSTTopDown(node.right, max,
        node.value + 1);
  }

  // bottom-up
  static double[] isBSTBottomUp(BSTNode node) {
    if (node == null)
      return new double[] {Integer.MAX_VALUE, Integer.MIN_VALUE};

    double[] range = {node.value, node.value};
    double[] rangeLeft = isBSTBottomUp(node.left);
    if (rangeLeft == null || node.value <= rangeLeft[1])
      return null;
    range[0] = Math.min(range[0], rangeLeft[0]);
    range[1] = Math.max(range[1], rangeLeft[1]);

    double[] rangeRight = isBSTBottomUp(node.right);
    if (rangeRight == null || node.value >= rangeRight[0])
      return null;
    range[0] = Math.min(range[0], rangeRight[0]);
    range[1] = Math.max(range[1], rangeRight[1]);
    return range;
  }

  static boolean isBalanced(BSTNode root) {
    return balanceHeight(root) != -1;
  }

  private static int balanceHeight(BSTNode root) {
    if (root == null) { return 0; }

    int leftHeight = balanceHeight(root.left);
    if (leftHeight == -1) { return -1; }

    int rightHeight = balanceHeight(root.right);
    if (rightHeight == -1) { return -1; }

    if (Math.abs(leftHeight - rightHeight) > 1) { return -1; }

    return 1 + Math.max(leftHeight, rightHeight);
  }

  public static BSTNode lowestCommonAncestor(BSTNode cur, String n1Id, String n2Id) {
    if (cur == null) { return null; }
    if (n1Id == null) { return new BSTNode(n2Id); }
    if (n2Id == null) { return new BSTNode(n1Id); }

    if (cur.id.equals(n1Id) || cur.id.equals(n2Id)) {
      return cur;
    }

    if (parseInt(cur.id) > parseInt(n1Id) && parseInt(cur.id) > parseInt(n2Id)) {
      return lowestCommonAncestor(cur.left, n1Id, n2Id);
    }

    if (parseInt(cur.id) < parseInt(n1Id) && parseInt(cur.id) < parseInt(n2Id)) {
      return lowestCommonAncestor(cur.right, n1Id, n2Id);
    }

    if (lowestCommonAncestor(cur.left, n1Id, n2Id) != null
        && lowestCommonAncestor(cur.right, n1Id, n2Id) != null) {
      return cur;
    }

    return null;
  }

  public static int longestOnesidePath(BSTNode root) {
    int[] maxLen = new int[1];
    longestOnesidePath(new DirectedBSTNode(root), null, 0, maxLen);
    return maxLen[0];
  }

  static void longestOnesidePath(DirectedBSTNode cur, DirectedBSTNode parent, int dir, int[] maxLen) {
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

  /**
   * max depth difference between two leaves
   */
  public static int maxDepthDifferenceBtwLeaves(BSTNode root) {
    if (root == null)
      return 0;
    else {
      int[] depthPair = new int[] {Integer.MAX_VALUE, 0};
      maxDepthDifferenceBtwLeaves(root, 0, depthPair);
      return depthPair[1] - depthPair[0];
    }
  }

  static void maxDepthDifferenceBtwLeaves(BSTNode cur, int curDepth, int[] depthPair) {
    if (cur == null)
      return;
    if (cur.right == null && cur.left == null) {
      depthPair[0] = Math.min(depthPair[0], curDepth);
      depthPair[1] = Math.max(depthPair[1], curDepth);
    } else {
      maxDepthDifferenceBtwLeaves(cur.left, curDepth + 1, depthPair);
      maxDepthDifferenceBtwLeaves(cur.right, curDepth + 1, depthPair);
    }
  }

  /**
   * max distance between two leaves
   */
  public static int maxDistanceBtwLeaves(BSTNode root) {
    if (root == null) return 0;
    else {
      int[] maxDis = new int[] {0};
      maxDistanceBtwLeaves(root, maxDis);
      return maxDis[0];
    }
  }

  static int maxDistanceBtwLeaves(BSTNode cur, int[] maxDis) {
    if (cur == null) return 0;
    int leftHeight = maxDistanceBtwLeaves(cur.left, maxDis);
    int rightHeight = maxDistanceBtwLeaves(cur.right, maxDis);
    if (leftHeight > 0 && rightHeight > 0 && leftHeight + rightHeight > maxDis[0]) {
      maxDis[0] = leftHeight + rightHeight;
    }
    return Math.max(leftHeight, rightHeight) + 1;
  }

  /**
   * minValue function
   */
  public static double minValue(BSTNode root) {
    return minValue(root, Integer.MAX_VALUE);
  }

  static double minValue(BSTNode cur, double value) {
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

  public static BSTNode mirror(BSTNode cur) {
    if (cur != null) {
      BSTNode tmp = mirror(cur.left);
      cur.left = mirror(cur.right);
      cur.right = tmp;
    }
    return cur;
  }

  public static List<String> pathBetweenTwoNodes(BSTNode root, String node1Id, String node2Id) {
    BSTNode lca = lowestCommonAncestor(root, node1Id, node2Id);
    List<String> path1 = pathFromChildToAncestorR(lca, node1Id);
    List<String> path2 = pathFromChildToAncestorR(lca, node2Id);
    reverse(path2);
    path2.remove(0);
    path1.addAll(path2);
    return path1;
  }

  public static List<String> pathFromChildToAncestorR(BSTNode ancestor, String childId) {
    List<String> path = new LinkedList<>();
    if (ancestor == null) return path;
    if (ancestor.id.equals(childId)) {
      path.add(childId);
      return path;
    }
    List<String> leftPath = pathFromChildToAncestorR(ancestor.left, childId);
    if (!leftPath.isEmpty()) {
      leftPath.add(ancestor.id);
      return leftPath;
    }
    List<String> rightPath = pathFromChildToAncestorR(ancestor.right, childId);
    if (!rightPath.isEmpty()) {
      rightPath.add(ancestor.id);
      return rightPath;
    }
    return path;
  }

  public static void printTree(BSTNode root, TraversalMethod method) {
    switch (method) {
      case INORDER_ITERATIVE_WITH_STACK:
        printTreeInorderI(root);
        break;
      case INORDER_ITERATIVE_WITHOUT_STACK:
        printTreeInorderMorris(root);
        break;
      case INORDER_RECUSRIVE:
        printTreeInorderR(root);
      case PREORDER_ITERATIVE_WITH_STACK:
        printTreePreorderI(root);
        break;
      case PREORDER_RECUSRIVE:
        printTreePreorderR(root);
        break;
      case POSTORDER_ITERATIVE_WITH_STACK:
        printTreePostorderI(root);
        break;
      case POSTORDER_RECUSRIVE:
        printTreePostorderR(root);
        break;
      default:
        throw new IllegalArgumentException(method + " does not exists");
    }
    System.out.println();
  }

  public static void printTreeByColumn(BSTNode root) {
    Map<Integer, List<String>> columns = new HashMap<>();
    printTreeByColumn(root, columns, 0);
    List<Integer> sortedColumnIdx = new ArrayList(columns.keySet());
    Collections.sort(sortedColumnIdx);
    for (int idx : sortedColumnIdx) {
      System.out.println(columns.get(idx));
    }
  }

  static void printTreeByColumn(BSTNode cur, Map<Integer, List<String>> columns, int columnIdx) {
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

  public static void printTreeByRows(BSTNode root) {
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

  public static void printTreeInorderR(BSTNode cur) {
    if (cur == null) {
      return;
    }
    printTreeInorderR(cur.left);
    System.out.println(cur);
    printTreeInorderR(cur.right);
  }

  public static void printTreeInorderI(BSTNode cur) {
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

  /**
   * Morris Traversal is an implementation of threaded binary tree.
   * reference: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
   */
  public static void printTreeInorderMorris(BSTNode cur) {
    BSTNode prev;
    while (cur != null) {
      if (cur.left == null) {
        System.out.println(cur);
        cur = cur.right;
      } else {
        // find predecessor
        prev = cur.left;
        while (prev.right != null && prev.right != cur) {
          prev = prev.right;
        }

        if (prev.right == null) {
          prev.right = cur;
          cur = cur.left;
        } else {
          prev.right = null;
          System.out.println(cur);
          cur = cur.right;
        }
      }
    }
  }

  public static void printTreePostorderI(BSTNode cur) {
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

  public static void printTreePreorderR(BSTNode cur) {
    if (cur == null) {
      return;
    }
    System.out.println(cur);
    printTreePreorderR(cur.left);
    printTreePreorderR(cur.right);
  }

  public static void printTreePreorderI(BSTNode cur) {
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

  public static void printTreeDFS(BSTNode root, String order) {
    switch (order) {
      case "pre":
        printTreePreorderR(root);
        break;
      case "post":
        printTreePostorderR(root);
        break;
      default:
        printTreeInorderR(root);
    }
    System.out.println();
  }

  public static void printTreePostorderR(BSTNode cur) {
    if (cur == null) {
      return;
    }
    printTreePostorderR(cur.left);
    printTreePostorderR(cur.right);
    System.out.println(cur);
  }

  /** printPaths */
  public static void printPaths(BSTNode root) {
    if (root == null) return;
    HashMap<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>();
    allPaths.put("", new ArrayList<String>()); // empty parent path for root
    printPaths(root, "", allPaths);
  }

  static void printPaths(BSTNode cur, String parentKey, HashMap<String, ArrayList<String>> allPaths) {
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

  /** Return the number of nodes in the tree whose key is smaller than the given key. */
  public static int rankOf(BSTNode cur, String key) {
    if (cur == null) return 0;

    int comp = parseInt(cur.id) - parseInt(key);

    if(comp > 0) {
      return rankOf(cur.left, key);
    }

    int leftSize = cur.left == null ? 0 : cur.left.size;

    if (comp == 0) {
      return leftSize;
    }
    return leftSize + 1 + rankOf(cur.right, key);
  }

  /** check if two trees are identical. */
  public static boolean sameTree(BSTNode one, BSTNode another) {
    if (one == null && another == null)
      return true;
    else {
      if (one == null || another == null || one.value != another.value)
        return false;
      else
        return sameTree(one.left, another.left) && sameTree(one.right, another.right);
    }
  }

  public static BSTNode search(BSTNode cur, String key) {
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

  /**
   * size of tree
   */
  public static int size(BSTNode cur) {
    if (cur == null) return 0;
    return size(cur.left) + size(cur.right) + 1;
  }

  /**
   * """ For the key values 1...numKeys, how many structurally unique binary search trees are
   * possible that store those keys? print all these trees in the inorder Strategy: consider that
   * each value could be the root. Recursively find the size of the left and right subtrees. """
   */
  public static void printAllBinaryTreesPermutation(int n) {
    ArrayList<BSTNode> trees = buildAllBinaryTreesPermutation(1, n);
    for (BSTNode t : trees) {
      printTreeByRows(t);
      System.out.println();
    }
  }

  public static void prettyPrint(BSTNode root) {
    HashMap<Integer, List<TreeBoardCell>> columns = new HashMap<>();
    int[] colIdxRange = new int[] {0, 0};
    int[] rowIdxRange = new int[] {0, 0};
    prettyPrint(null, root, columns, 0, 0, colIdxRange, rowIdxRange, 1);
    TreeBoard tb = new TreeBoard(columns, colIdxRange, rowIdxRange[1] + 1);
    System.out.println(tb.toString());
  }

  static void prettyPrint(BSTNode parent, BSTNode cur, HashMap<Integer, List<TreeBoardCell>> columns, int curCol, int curRow, int[] colIdxRange, int[] rowIdxRange, int nLineBetweenLevels) {
    if (cur.left != null) {
      for (int i = 1; i <= nLineBetweenLevels; i++) {
        upsert(columns, curRow + i, curCol - i, "/", false);
      }
      prettyPrint(cur, cur.left, columns, curCol - nLineBetweenLevels - 1,
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
      prettyPrint(cur, cur.right, columns, curCol + nLineBetweenLevels + 1,
          curRow + nLineBetweenLevels + 1, colIdxRange, rowIdxRange, nLineBetweenLevels);
    }
  }

  static void upsert(HashMap<Integer, List<TreeBoardCell>> columns,
      int curRow, int curCol, String val, boolean isRightChild) {
    List<TreeBoardCell> column = columns.get(curCol);
    if (column == null) {
      column = new LinkedList<>();
    }
    column.add(new TreeBoardCell(isRightChild, curRow, curCol, val));
    columns.put(curCol, column);
  }

  public static void printPrettyTreeOld(BSTNode root) {
    // System.out.println(String.format("%-3d%5d", 12, 3));

    HashMap<BSTNode, Integer> spaceAhead = new HashMap<>();
    int[] cumSum = new int[] {0};
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

  public static boolean subtree(BSTNode t1, BSTNode t2) {
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
  public static BSTNode successorInorder(BSTNode root, int value) {
    if (root == null)
      return root;
    return successorInorder(root, value, new int[] {0});
  }

  static BSTNode successorInorder(BSTNode cur, int value, int[] isFound) {
    if (cur == null)
      return cur;
    // first search in left subtree
    BSTNode left = successorInorder(cur.left, value, isFound);
    if (left != null)
      return left;

    // if already found the value, return cur
    if (isFound[0] == 1)
      return cur; // next Node;
    if (cur.value == value)
      isFound[0] = 1;

    // continue search in right subtree
    return successorInorder(cur.right, value, isFound);
  }

  static BSTNode balanceTree(BSTNode root) {
    ArrayList<BSTNode> nodes = new ArrayList<>();
    while (root != null) {
      nodes.add(root);
      root = root.right;
    }
    return balanceTreeRecursive(nodes);
  }

  static BSTNode balanceTreeRecursive(List<BSTNode> nodes) {
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
  static void calSpaceAhead(BSTNode root, HashMap<BSTNode, Integer> spaceAhead, int[] cumSum) {
    if (root == null)
      return;
    calSpaceAhead(root.left, spaceAhead, cumSum);
    // System.out.println(root.value+" : "+cumSum[0]);
    spaceAhead.put(root, cumSum[0]);
    cumSum[0] += numberLength(root.value);
    calSpaceAhead(root.right, spaceAhead, cumSum);
  }

  static int numberLength(double num) {
    return String.valueOf(num).length();
  }

  // merge sort
  public static BSTNode merge(BSTNode l1, BSTNode l2) {
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

  static void putNumberInLine(char[][] prettyTree, int row, int startCol, String value) {
    String v = String.valueOf(value);
    for (int j = 0; j < v.length(); j++)
      prettyTree[row][j + startCol] = value.charAt(0);
  }
}

class BSTNodeDisWrapper {
  BSTNode node;
  int dis;

  public BSTNodeDisWrapper(BSTNode node, int dis) {
    this.node = node;
    this.dis = dis;
  }
}

class DirectedBSTNode extends BSTNode {
  int direction;
  int len;

  public DirectedBSTNode(BSTNode node) {
    super(node);
  }
}

class TreeBoard {
  List<List<TreeBoardCell>> cells;

  public TreeBoard(HashMap<Integer, List<TreeBoardCell>> columns, int[] columnIdxRange, int nRows) {
    cells = new LinkedList<>();
    for (int i = 0; i < nRows; i++) cells.add(new LinkedList<TreeBoardCell>());

    List<Integer> sortedColumnIdx = new ArrayList<>(columns.keySet());
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

class TreeBoardCell {
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
