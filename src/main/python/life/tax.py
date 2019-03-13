"""
Args:
  agi: adjusted gross income
  tax_brackets: list of triples
"""
def calculate_tax(agi, tax_brackets):
  tax_brackets = sorted(tax_brackets)
  tax = 0
  for lo, hi, rate in tax_brackets:
    if lo > agi:
      break
    tax += (min(hi, agi) - lo) * (rate / 100.)
  return tax, tax / agi * 100

def generate_tax_brackets(brackets_higher_bounds, rates):
  assert len(brackets_higher_bounds) + 1 == len(rates)
  brackets = []
  lower_bound = 0
  for i in range(len(brackets_higher_bounds)):
    brackets.append((lower_bound, brackets_higher_bounds[i], rates[i]))
    lower_bound = brackets_higher_bounds[i] + 1
  brackets.append((lower_bound, 2 << 32, rates[-1]))
  return brackets

MARRIED_JOINTLY_2017 = generate_tax_brackets(
  [18650, 75900, 153100, 233500, 416700, 470700],
  [10, 15, 25, 28, 33, 35, 39.6],
)
MARRIED_JOINTLY_2018 = generate_tax_brackets(
  [19050, 77400, 165000, 315000, 400000, 600000],
  [10, 12, 22, 24, 32, 35, 37],
)

for agi in range(300000, 500001, 50000):
  print(agi, "(%.2f, %.2f)" % calculate_tax(agi, MARRIED_JOINTLY_2017), "(%.2f, %.2f)" % calculate_tax(agi, MARRIED_JOINTLY_2018))

agi = list(range(1, 500000, 1000))
import functools
tax_series_2017 = map(functools.partial(calculate_tax, tax_brackets=MARRIED_JOINTLY_2017), agi)
tax_series_2018 = map(functools.partial(calculate_tax, tax_brackets=MARRIED_JOINTLY_2018), agi)

import pylab
pylab.plt.scatter(agi, [tax for tax, rate in tax_series_2017], s=[10], c=['r'], alpha=0.5)
pylab.plt.scatter(agi, [tax for tax, rate in tax_series_2018], s=[10], c=['g'], alpha=0.5)

pylab.plt.grid()
pylab.plt.show()
