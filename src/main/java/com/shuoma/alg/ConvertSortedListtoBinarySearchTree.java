package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;
import com.shuoma.ds.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
@Tag(dl = D2, dss = BinaryTree, references = LeetCode)
public class ConvertSortedListtoBinarySearchTree {
  //second pass
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null)
      return null;

    ArrayList<Integer> array = new ArrayList<>();
    ListNode cur = head;
    while (cur != null) {
      array.add(cur.val);
      cur = cur.next;
    }
    return sortedArrayToBST(array.toArray(new Integer[1]));

  }

  public TreeNode sortedArrayToBST(Integer[] num) {
    int n = num.length;
    if (n == 0)
      return null;
    int m = n / 2;
    TreeNode root = new TreeNode((int) num[m]);
    root.left = sortedArrayToBST(Arrays.copyOfRange(num, 0, m));
    root.right = sortedArrayToBST(Arrays.copyOfRange(num, m + 1, n));
    return root;
  }

  //first pass
  public TreeNode sortedListToBSTFirstPass(ListNode head) {
    ArrayList<ListNode> li = new ArrayList<ListNode>();
    if (head == null)
      return null;
    ListNode cur = head;
    while (cur != null) {
      li.add(new ListNode(cur.val));
      cur = cur.next;
    }
    return sortedArrayToBST(li);
  }

  public TreeNode sortedArrayToBST(List<ListNode> li) {
    int n = li.size();
    if (n == 0)
      return null;
    int mid = n / 2;
    TreeNode root = new TreeNode(li.get(mid).val);
    root.right = sortedArrayToBST(li.subList(mid + 1, n));
    root.left = sortedArrayToBST(li.subList(0, mid));
    return root;
  }
}
