def memoize(hash_func = lambda args : args):
  def memoize_wrap(f):
    mem = {}
    def memorized(*args):
      hash_value = hash_func(*args)
      if hash_value not in mem:
        # Note args are wrapped as a tuple not list, so it is hashable
        mem[hash_value] = f(*args)
      return mem[hash_value]
    return memorized
  return memoize_wrap

def timer(f):
  def timed(*args, **kwargs):
    import time
    start = time.time()
    ret = f(*args, **kwargs)
    end = time.time()
    print("running time (in secs): " + str(end - start))
    return ret
  return timed

class Pipe(object):
  def __init__(self, func):
    self.func = func

  def __ror__(self, other):
    def generator():
      for obj in other:
        if obj is not None:
          yield self.func(obj)
    return generator()
