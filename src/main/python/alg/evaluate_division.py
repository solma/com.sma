"""
Equations are given in the format A / B = k, where A and B are variables represented as strings,
and k is a real number (floating point number). Given some queries, return the answers.
If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations,
vector<double>& values, vector<pair<string, string>> queries ,
where equations.size() == values.size(), and the values are positive.
This represents the equations. Return vector<double>.

According to the example above:

equations = [ ['a', 'b'], ['b', 'c'] ],
values = [2.0, 3.0],
queries = [ ['a', 'c'], ['b', 'a'], ['a', 'e'], ['a', 'a'], ['x', 'x'] ].
The input may be not valid, e.g. a/b = 2 and a/b = 3. If the input valid, return an error message.
You may assume that evaluating the queries will result in no division by zero.
"""

from alg.label import *
Label(Label.DirectedGraph, Label.UnionFind, Label.LeetCode)

from collections import defaultdict

_INVALID_INPUT_ERROR = 'InvalidInput'


class EvaluateDivision(object):
  def calcEquation(self, equations, values, queries):
    variables = {}
    cluster_members = defaultdict(set)
    cluster_lead = {}

    def assign_values():
      for i, eq in enumerate(equations):
        numerator, denominator = eq

        if numerator not in cluster_lead and denominator not in cluster_lead:
          cluster_lead[numerator] = numerator
          cluster_lead[denominator] = numerator
          variables[numerator] = values[i]
          variables[denominator] = 1
          cluster_members[numerator].add(numerator)
          cluster_members[numerator].add(denominator)
          continue
        if numerator in cluster_lead and denominator not in cluster_lead:
          lead = cluster_lead[numerator]
          cluster_lead[denominator] = lead
          cluster_members[lead].add(denominator)
          variables[denominator] = variables[numerator] / values[i]
          continue
        if numerator not in cluster_lead and denominator in cluster_lead:
          lead = cluster_lead[denominator]
          cluster_lead[numerator] = lead
          cluster_members[lead].add(numerator)
          variables[numerator] = variables[denominator] * values[i]
          continue
        if numerator in cluster_lead and denominator in cluster_lead:
          numerator_lead, denominator_lead = cluster_lead[numerator], cluster_lead[denominator]
          if numerator_lead == denominator_lead:
            if variables[numerator] != variables[denominator] * values[i]:
              return _INVALID_INPUT_ERROR
            else:
              continue
          # Merge two clusters
          else:
            scale_factor = (variables[numerator] / values[i]) / variables[denominator]
            for member in cluster_members[denominator_lead]:
              cluster_lead[member] = numerator_lead
              cluster_members[numerator_lead].add(member)
              variables[member] *= scale_factor
            del cluster_members[denominator_lead]

    def evaluate():
      ret = []
      for q in queries:
        numerator, denominator = q
        if numerator in cluster_lead and denominator in cluster_lead and cluster_lead[numerator] == cluster_lead[denominator]:
          ret.append(variables[numerator] / variables[denominator])
        else:
          ret.append(-1)
      return ret

    error_msg = assign_values()
    if error_msg:
      return error_msg
    return evaluate()

  def calcEquationGraph(self, equations, values, queries):
    g = defaultdict(lambda: defaultdict(float))
    g_updated = defaultdict(lambda: defaultdict(bool))
    for (s, t), v in zip(equations, values):
      g[s][t] = v
      g[t][s] = 1.0 / v
    for k in g:
      g[k][k] = 1.0
      for s in g:
        for t in g:
          if g[s][k] and g[k][t]:
            new_value = g[s][k] * g[k][t]
            # if g_updated[s][t] and new_value != g[s][t]:
            #   return _INVALID_INPUT_ERROR
            g[s][t] = new_value
            g_updated[s][t] = True

    ans = []
    for s, t in queries:
      ans.append(g[s][t] if g[s][t] else -1.0)
    return ans


ins = EvaluateDivision()
valid_cases = [
  ([['x1', 'x2'], ['x2', 'x3'], ['x1', 'x4'], ['x2', 'x5']],
   [3.0, 0.5, 3.4, 5.6],
   [['x2', 'x4'], ['x1', 'x5'], ['x1', 'x3'], ['x5', 'x5'], ['x5', 'x1'], ['x3', 'x4'], ['x4', 'x3'], ['x6', 'x6'],
    ['x0', 'x0']]),
  ([['a', 'e'], ['b', 'e']],
   [4.0, 3.0],
   [['a', 'b'], ['e', 'e'], ['x', 'x']]),
  ([['a', 'b'], ['e', 'f'], ['b', 'e']], [3, 2, 5],
   [['b', 'a'], ['a', 'f'], ['f', 'f'], ['e', 'e'], ['c', 'c'], ['a', 'c'], ['f', 'e']]),
]
invalid_cases = [
  ([['a', 'e'], ['b', 'e'], ['a', 'b']],
   [4.0, 2.0, 3.],
   [['a', 'b'], ['e', 'e'], ['x', 'x']]),
]


import numpy

for i, case in enumerate(valid_cases[:]):
  equations, values, queries = case[0], case[1], case[2]
  ans1 = ins.calcEquation(equations, values, queries)
  ans2 = ins.calcEquationGraph(equations, values, queries)
  if ((ans1 != ans2 and (ans1 == _INVALID_INPUT_ERROR or ans2 == _INVALID_INPUT_ERROR))
      or (not numpy.allclose(ans1, ans2))):
    print('case %s:\n ans1 = %s\n ans2 = %s' % (str(i), ans1, ans2))

for case in invalid_cases:
  print(ins.calcEquation(case[0], case[1], case[2]))
