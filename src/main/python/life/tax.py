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
  return tax

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
TRUMP_TAX_CODE = generate_tax_brackets(
  [90000, 260000, 1000000],
  [12, 25, 35, 39.6],
)

for agi in [100000, 200000, 300000, 400000, 500000]:
  print (agi, calculate_tax(agi, MARRIED_JOINTLY_2017), calculate_tax(agi, TRUMP_TAX_CODE))

agi = list(range(0, 500000, 1000))
import functools
tax_1 = map(functools.partial(calculate_tax, tax_brackets=MARRIED_JOINTLY_2017), agi)
tax_2 = map(functools.partial(calculate_tax, tax_brackets=TRUMP_TAX_CODE), agi)

import pylab
pylab.plt.scatter(agi, tax_1, s=[10], c=['r'], alpha=0.5)
pylab.plt.scatter(agi, tax_2, s=[10], c=['g'], alpha=0.5)

pylab.plt.grid()
pylab.plt.show()
