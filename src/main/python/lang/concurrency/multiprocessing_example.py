from multiprocessing.pool import ThreadPool
import itertools as it

from utils.decorators import elapsedtime
from lang.functional.partial_example import CreateFamily
from lang.functional.partial_example import PrintFullName
from lang.functional.partial_example import Family
from lang.functional.partial_example import Name

@elapsedtime
def PoolExample():
  first_names = ['Sol', 'Michelle', 'Lexi']
  first_names_1 = ['loS', 'ele', 'ixe']
  ma_family = Family('Ma')
  # ma_family.Add('Sol')
  # print ma_family

  for i in range(1):
    pool1, pool2 = ThreadPool(2), ThreadPool(2)
    # pool1_names = pool1.map(PrintFullNameWrapper, it.izip(first_names, it.repeat(ma_family.last_name)))
    # pool2_names = pool1.map(CreateFamily('Xie'), first_names)
    # print '#%d names:\n%s' % (i, '\n'.join(pool1_names + pool2_names))
    pool1.map(FamilyAddWrapper, it.izip(it.repeat(ma_family), first_names))
    # pool1.map(FamilyAddWrapper, it.izip(it.repeat(ma_family), first_names_1))
  print ma_family

def FamilyAddWrapper(args):
  family = args[0]
  family.Add(*args[1:])

def PrintFullNameWrapper(args):
  return PrintFullName(*args)

if __name__ == '__main__':
  PoolExample()
