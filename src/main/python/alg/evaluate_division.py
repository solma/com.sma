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

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that
evaluating the queries will result in no division by zero and there is no contradiction.
"""

from alg.label import Label
Label(Label.DirectedGraph, Label.UnionFind, Label.LeetCode)

from collections import defaultdict
class EvaluateDivision(object):
  def calcEquation(self, equations, values, queries):
    multiples = defaultdict(list)
    variables = {}
    parent = {}

    def get_cluster(c):
      x = c
      while x in parent:
        x = parent[x]
      return x

    def assign_values():
      for i, eq in enumerate(equations):
        numerator, denominator = eq
        parent[get_cluster(denominator)] = get_cluster(numerator)
        multiples[denominator].append(numerator)
        multiples[denominator] += multiples[numerator]

        if denominator not in variables:
          if numerator not in variables:
            variables[denominator] = 1
            variables[numerator] = values[i]
          else:
            variables[denominator] = variables[numerator] / values[i]
        else:
          variables[numerator] = values[i] * variables[denominator]
          for d in multiples[numerator]:
            variables[d] *= values[i] * variables[denominator]
        print variables, multiples

    def evaluate():
      ret = []
      for q in queries:
        numerator, denominator = q
        if numerator == denominator:
          ret.append(1 if numerator in variables else -1)
        elif get_cluster(numerator) != get_cluster(denominator):
          ret.append(-1)
        else:
          ret.append(variables[numerator] / variables[denominator])
      return ret

    assign_values()
    return evaluate()

  def calcEquationGraph(self, equations, values, queries):
    g = defaultdict(lambda: defaultdict(int))
    for (s, t), v in zip(equations, values):
      g[s][t] = v
      g[t][s] = 1.0 / v
    for k in g:
      g[k][k] = 1.0
      for s in g:
        for t in g:
          if g[s][k] and g[k][t]:
            g[s][t] = g[s][k] * g[k][t]
    ans = []
    for s, t in queries:
      ans.append(g[s][t] if g[s][t] else -1.0)
    return ans

ins = EvaluateDivision()
cases = [
  ([["x1","x2"],["x2","x3"],["x1","x4"],["x2","x5"]],
   [3.0,0.5,3.4,5.6],
   [["x2","x4"],["x1","x5"],["x1","x3"],["x5","x5"],["x5","x1"],["x3","x4"],["x4","x3"],["x6","x6"],["x0","x0"]]),
  ([["a","e"],["b","e"]],
   [4.0,3.0],
   [["a","b"],["e","e"],["x","x"]]),
  ([["a","b"],["e","f"],["b","e"]], [3.4,1.4,2.3],
   [["b","a"],["a","f"],["f","f"],["e","e"],["c","c"],["a","c"],["f","e"]])
]
for case in cases[-1:]:
  print ins.calcEquationGraph(case[0], case[1], case[2])




