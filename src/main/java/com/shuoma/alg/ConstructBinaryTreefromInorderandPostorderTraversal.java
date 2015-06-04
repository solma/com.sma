package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dl = D2, dss = BinaryTree, reference = LeetCode)
public class ConstructBinaryTreefromInorderandPostorderTraversal {
  //second pass
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  public TreeNode buildTree(int[] inorder, int iLo, int iHi, int[] postorder, int pLo, int pHi) {
    if (iLo > iHi)
      return null;

    int i;
    for (i = iLo; i <= iHi; i++)
      if (inorder[i] == postorder[pHi])
        break;
    if (i > iHi)
      return null; //error

    int pM = pLo + (i - 1 - iLo);
    TreeNode tree = new TreeNode(postorder[pHi]);
    tree.left = buildTree(inorder, iLo, i - 1, postorder, pLo, pM);
    tree.right = buildTree(inorder, i + 1, iHi, postorder, pM + 1, pHi - 1);

    return tree;
  }

  //first pass
  public TreeNode buildTreeFirstPass(int[] inorder, int[] postorder) {
    if (inorder.length == 0)
      return null;
    if (inorder.length == 1)
      return new TreeNode(inorder[0]);

    // the last item in postorder is root
    TreeNode root = new TreeNode(postorder[postorder.length - 1]);

    int i = inorder.length - 1;
    for (; inorder[i] != root.val; i--)
      ;

    // inorder.length == postorder.length
    if (i < inorder.length - 1) {
      root.right = buildTree(Arrays.copyOfRange(inorder, i + 1, inorder.length),
          Arrays.copyOfRange(postorder, i, postorder.length - 1));
    }
    if (i > 0) {
      root.left = buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0, i));
    }

    return root;
  }
}
