from multiprocessing.pool import ThreadPool

def append_and_print(tuple):
  l, x = tuple
  print(x)
  l.append(x)

if __name__ == '__main__':
  # Add [0, n - 1] to a list using 10 threads
  pool = ThreadPool(10)
  l = []
  n = 100
  pool.map(append_and_print, zip([l] * n, range(n)))
  print(l)
