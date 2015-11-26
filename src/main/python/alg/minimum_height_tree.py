"""
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted
tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a
graph, write a function to find all the MHTs and return a list of their root labels in ascending order.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected
edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
as [1, 0] and thus will not appear together in edges.

Example 1:
Given n=4, edges = [[1, 0], [1, 2], [1, 3]]

  0
  |
  1
 / \
2  3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

0 1 2
 \|/
  3
  |
  4
  |
  5
return [3, 4]
"""

from src.main.python.alg.util.graph_helper import to_adjacent_lists
from src.main.python.alg.label import Label

Label(Label.Tree, Label.BreadthFirstSearch, Label.DepthFirstSearch, Label.LeetCode)

class Solution(object):
  def findMinHeightTrees(self, n, edges):
    children = to_adjacent_lists(edges)
    vertices = set(children.keys())

    # delete leaves until one/two are left
    while len(vertices) > 2:
      leaves = [x for x in children if len(children[x]) == 1]
      for x in leaves:
        for y in children[x]:
          children[y].remove(x)
        del children[x]
        vertices.remove(x)
    return list(vertices) if n != 1 else [0]
