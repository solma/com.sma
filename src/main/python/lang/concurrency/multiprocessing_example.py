from multiprocessing.pool import ThreadPool
import itertools as it

def append_and_print(t):
  l, x = t
  print x
  l.append(x)

if __name__ == '__main__':
  # PoolExample()
  pool = ThreadPool(10)
  l = []
  n = 100
  pool.map(append_and_print, it.izip([l] * n, range(n)))
