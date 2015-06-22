package com.shuoma.alg;

import static com.shuoma.annotation.Tag.DataStructure.BinaryTree;
import static com.shuoma.annotation.Tag.Difficulty.D2;
import static com.shuoma.annotation.Tag.Reference.LeetCode;

import com.shuoma.annotation.Tag;

import java.util.Arrays;

@Tag(dl = D2, dss = BinaryTree, references = LeetCode)
public class ConstructBinaryTreefromInorderandPreorderTraversal {
  //second pass
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  public TreeNode buildTree(int[] preorder, int pLo, int pHi, int[] inorder, int iLo, int iHi) {
    if (iLo > iHi)
      return null;

    int i;
    for (i = iLo; i <= iHi; i++)
      if (inorder[i] == preorder[pLo])
        break;
    if (i > iHi)
      return null; //error

    int pM = pLo + 1 + (i - 1 - iLo);
    TreeNode tree = new TreeNode(preorder[pLo]);
    tree.left = buildTree(preorder, pLo + 1, pM, inorder, iLo, i - 1);
    tree.right = buildTree(preorder, pM + 1, pHi, inorder, i + 1, iHi);

    return tree;
  }

  //first pass
  public TreeNode buildTreeFirstPass(int[] preorder, int[] inorder) {
    if (inorder.length == 0)
      return null;
    if (inorder.length == 1)
      return new TreeNode(inorder[0]);

    // the last item in postorder is root
    TreeNode root = new TreeNode(preorder[0]);

    int i = 0;
    for (; inorder[i] != root.val; i++)
      ;

    // inorder.length == preorder.length
    if (i < inorder.length - 1) {
      root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length),
          Arrays.copyOfRange(inorder, i + 1, inorder.length));
    }
    if (i > 0) {
      root.left =
          buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
    }

    return root;
  }
}
