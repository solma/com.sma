from collections import defaultdict
from alg.label import Label
Label(Label.Array, Label.GraphTheory, Label.Interview, Label.LeetCode)

class MergeIntersectedSets(object):
  # O(n^2m)
  def merge_intersected_sets(self, list_of_sets):
    ret_sets = set()
    while len(list_of_sets) > 0:
      cur, rest = list_of_sets[0], list_of_sets[1:]

      last_size = -1
      while len(cur) > last_size:
        disjoints_sets = []
        last_size = len(cur)
        for aset in rest:
          if len(cur & aset) > 0:
            cur |= aset
          else:
            disjoints_sets.append(aset)
        rest = disjoints_sets

      list_of_sets = rest
      ret_sets.add(frozenset(cur))
    return ret_sets

  """
  Convert the problem to find connected components of a graph O(nm)
  """
  def merge_intersected_sets_1(self, list_of_sets):
    def build_graph():
      neighbors = defaultdict(set)
      for aset in list_of_sets:
        for c in aset:
          neighbors[c] |= aset
      return neighbors

    def dfs(cur_node):
      visited[cur_node] = component_label
      cur_set.add(cur_node)
      for next_node in neighbors[cur_node]:
        if visited[next_node] == 0:
          dfs(next_node)

    neighbors = build_graph()
    visited = defaultdict(int)
    ret_sets = set()
    component_label = 0
    for cur_node in neighbors.keys():
      if visited[cur_node] == 0:
        component_label += 1
        cur_set = set()
        dfs(cur_node)
        ret_sets.add(frozenset(cur_set))
    return ret_sets


def test():
  def manual_test_cases():
    manual_cases = [
      ([{1, 2, 3}, {3, 5}, {4, 10}, {5, 7}, {9, 10}],
       {frozenset([1, 2, 3, 5, 7]), frozenset([4, 9, 10])}),
    ]
    for case in manual_cases:
      res1 = ins.merge_intersected_sets(case[0])
      res2 = ins.merge_intersected_sets_1(case[0])
      assert case[1] == res1 == res2

  def automated_test_cases():
    def gen_rand_sets_of_sets(max_num_of_sets=7, max_num_of_elements=3):
      from utils.random_helper import gen_rand_array
      from utils.random_helper import r
      sets_of_sets = []
      for i in range(r.randint(2, max_num_of_sets)):
        sets_of_sets.append(set(gen_rand_array(max_num_of_elements, 10)))
      return sets_of_sets

    automated_cases = [gen_rand_sets_of_sets() for _ in range(100)]
    for case in automated_cases:
      res1 = ins.merge_intersected_sets(case)
      res2 = ins.merge_intersected_sets_1(case)
      if res1 != res2:
        print case
        print res1
        print res2
      assert res1 == res2

  ins = MergeIntersectedSets()
  manual_test_cases()
  automated_test_cases()

test()
