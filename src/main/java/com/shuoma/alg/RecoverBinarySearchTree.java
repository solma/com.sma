package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinarySearchTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(dl = D2, dss = BinarySearchTree, references = LeetCode)
public class RecoverBinarySearchTree {
  public static void main(String[] args) {
    new RecoverBinarySearchTree().main();
  }

  public void main() {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(7);
    root.right = new TreeNode(9);
    root.right.left = new TreeNode(1);
    recoverTree(root);
  }

  List<TreeNode> t;
  TreeNode previous;

  public void recoverTree(TreeNode root) {
    t = new ArrayList<>();
    previous = null;
    inorder(root);
    System.out.println(t);

    //swap the value of the first and last node in the list
    int temp = t.get(0).val;
    t.get(0).val = t.get(t.size() - 1).val;
    t.get(t.size() - 1).val = temp;
  }

  public void inorder(TreeNode cur) {
    if (cur == null) { return; }
    inorder(cur.left);
    if (previous != null && previous.val > cur.val) {
      if (!t.contains(previous)) { t.add(previous); }
      if (!t.contains(cur)) { t.add(cur); }
    }
    previous = cur;
    inorder(cur.right);
  }
}
