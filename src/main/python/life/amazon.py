import csv

def analysis(year):
  sum = 0
  file_name = '/home/solma/Downloads/01-Jan-%s_to_01-Jan-%s.csv' % (year, year + 1)
  with open(file_name, 'r') as f:
    reader = csv.reader(f)
    for i, line in enumerate(reader):
      if i == 0:
        continue
      dollar = float(line[29][1:])
      sum += dollar
  print(year, ':', '$%.2f' % sum)

for year in range(2016, 2018, 1):
  analysis(year)
