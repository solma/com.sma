package com.shuoma;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Source.LeetCode;

import com.shuoma.annotation.Tag;

@Tag(dl = D2, dss = BinaryTree, source = LeetCode)
public class FlattenBinaryTreetoLinkedList {
  public static void main(String[] args) {
    new FlattenBinaryTreetoLinkedList().main();
  }

  public void main() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.right = new TreeNode(4);
    flatten(root);
  }

  public void flatten(TreeNode root) {
    // if (root == null) return;
    // root=flattenRecursive(root)[0];
    root = flattenRecursive(root);
    System.out.println(root);
  }


  public TreeNode flattenRecursive(TreeNode cur) {
    if (cur == null)
      return cur;
    TreeNode rootOfL = flattenRecursive(cur.left);
    if (cur.left != null)
      cur.left.right = cur;
    cur.right = flattenRecursive(cur.right);
    return rootOfL == null ? cur : rootOfL;
  }

  // public TreeNode[] flattenRecursive(TreeNode cur) {
  // TreeNode[] firstAndLast=new TreeNode[2];
  // firstAndLast[0]=firstAndLast[1]=cur;
  // TreeNode[] next;
  // TreeNode firstOfRightSubtree=null;
  // if(cur.right!=null){
  // next=flattenRecursive(cur.right);
  // firstAndLast[1]=next[1];
  // firstOfRightSubtree=next[0];
  // }
  // if(cur.left!=null){
  // next=flattenRecursive(cur.left);
  // cur.left=null;
  // cur.right=next[0];
  // next[1].right=firstOfRightSubtree; //connecting left and right subtrees
  // if(firstAndLast[1].equals(cur)) firstAndLast[1]=next[1]; //update last
  // firstAndLast[0]=cur; //update first
  // }
  // return firstAndLast;
  // }


  //Non-recursive way

 /*
   * preOrder:
   * 1) check, if current node is null or leaf, return it.
   * 2) init a stack to store the right child tree
   * 3) preOrder travel the tree
   * 3.1) if current node has left child, store the right child in stack,
   *      change the left child to right.
   * 3.2) else, get the right child from the stack and
   *      set it as the right child as the current node
   *
   */
  // public void flatten(TreeNode root) {
  // if (root == null || (root.left == null && root.right == null)) return;
  // Stack<TreeNode> stack = new Stack<TreeNode>();
  // TreeNode curr = root;

  // while (curr != null || !stack.isEmpty()) {

  // while (curr.left != null) {
  // if(curr.right != null)  stack.push(curr.right);
  // curr.right = curr.left;
  // curr.left = null;
  // curr = curr.right;
  // }
  // if (curr.right == null && !stack.isEmpty()) curr.right = stack.pop();
  // curr = curr.right;
  // }
  // }
}
