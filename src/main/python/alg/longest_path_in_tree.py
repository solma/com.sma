from src.main.python.alg.util.graph_helper import to_adjacent_lists

#ref: http://cs.stackexchange.com/questions/11263/longest-path-in-an-undirected-tree-with-only-one-traversal
class LongestPathInTree(object):
  def height_and_longest_path(self, edges):
    return self.dfs(to_adjacent_lists(edges), 0, [False] * (len(edges) + 1))

  def dfs(self, neighbors, cur, visited):
    visited[cur] = True

    ret_height_path = [cur]
    ret_longest_path = longest_height_path = sec_longest_height_path = []
    if cur in neighbors.keys():
      for n in neighbors[cur]:
        if visited[n]:
          continue
        height_path, longest_path = self.dfs(neighbors, n, visited)

        if len(ret_height_path) < len(height_path) + 1:
          ret_height_path = height_path + [cur]
        if len(height_path) > len(sec_longest_height_path):
          if len(height_path) > len(longest_height_path):
            sec_longest_height_path = longest_height_path
            longest_height_path = height_path
          else:
            sec_longest_height_path = height_path

        if len(ret_longest_path) < len(longest_path):
          ret_longest_path = longest_height_path

    if len(ret_longest_path) < len(longest_height_path) + 2 + len(sec_longest_height_path):
      ret_longest_path = longest_height_path + [cur] + sec_longest_height_path

    return ret_height_path, ret_longest_path


if __name__ == '__main__':
  ins = LongestPathInTree()
  edges = [[0, 1], [0, 2]]
  #edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
  height_path, longest_path = ins.height_and_longest_path(edges)
  print('height path = %s'%height_path)
  print('longest path  = %s'%longest_path)
