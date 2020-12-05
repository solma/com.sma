import csv
import datetime
from collections import defaultdict

def amazon():

  def yearly_analysis(year):
    sum = 0
    file_name = '/Users/solma/Downloads/01-Jan-%s_to_01-Jan-%s.csv' % (year, year + 1)
    with open(file_name, 'r') as f:
      reader = csv.reader(f)
      for i, line in enumerate(reader):
        if i == 0:
          continue
        dollar = float(line[29][1:])
        sum += dollar
    print(year, ':', '$%.2f' % sum)

  for year in range(2016, 2018, 1):
    amazon(year)

def home():
  with open('/Users/solma/Downloads/mint_transactions.csv', 'r') as f:
    reader = csv.reader(f)
    total_cost = 0
    cost_map = defaultdict(float)
    for i, line in enumerate(reader):
      if i == 0:
        continue
      dt = datetime.datetime.strptime(line[0], '%m/%d/%Y')
      cost = float(line[3])
      notes = line[-1].lower()

      total_cost += cost
      cost_map[notes] += cost
    print('%.3f' % total_cost)
    li = [(k, cost_map[k]) for k in cost_map]
    li = sorted(li, key=lambda x: x[1], reverse=True)
    for k, v in li:
      print('%s: %.3f'% (k, v))

home()
