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

CHINESE_2018 = generate_tax_brackets(
  [3000, 12000, 25000, 35000, 55000, 80000],
  [3, 10, 20, 25, 30, 35, 45],
)

for agi in range(300000, 500001, 50000):
  print(agi, "(%.2f, %.2f)" % calculate_tax(agi, MARRIED_JOINTLY_2017), "(%.2f, %.2f)" % calculate_tax(agi, MARRIED_JOINTLY_2018))

agi = list(range(1, 500000, 1000))
import functools
import pylab

for tax_rates, color in zip(
    [
      MARRIED_JOINTLY_2017,
      MARRIED_JOINTLY_2018,
      CHINESE_2018,
    ],
    [
      'r',
      'g',
      'b',
    ],
):
  tax_series = map(functools.partial(calculate_tax, tax_brackets=tax_rates), agi)
  pylab.plt.scatter(agi, [tax for tax, rate in tax_series], s=[10], c=[color], alpha=0.5)
  pylab.plt.savefig('/tmp/tax.png')

pylab.plt.grid()
pylab.plt.show()
