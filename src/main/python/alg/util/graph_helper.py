def to_adjacent_lists(edges):
  import collections
  neighbors = collections.defaultdict(list)
  for e in edges:
    neighbors[e[0]].append(e[1])
    neighbors[e[1]].append(e[0])
  return neighbors
