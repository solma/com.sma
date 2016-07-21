"""
The thief has found himself a new place for his thievery again. There is only one entrance to this area,
called the "root." Besides the root, each house has one and only one parent house. After a tour,
the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the
police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
"""

from alg.label import Label

Label(Label.BinaryTree, Label.DynamicProgramming, Label.LeetCode, Label.LinearithmicTime)

class HouseRobberIII(object):
  def rob(self, root):
    def rob_dp(root):
      if root is None:
        return 0
      if root.left == root.right == None:
        return root.val
      if root in dp:
        return dp[root]
      dp[root] = max(root.val +
                     ((rob_dp(root.left.left) + rob_dp(root.left.right)) if root.left is not None else 0) +
                     ((rob_dp(root.right.left) + rob_dp(root.right.right)) if root.right is not None else 0),
                     rob_dp(root.left) + rob_dp(root.right))
      return dp[root]

    dp = dict()
    return rob_dp(root)
