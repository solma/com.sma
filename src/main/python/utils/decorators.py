def memoize(hash_func = lambda args : args):
  def memoize_wrap(f):
    mem = {}
    def memorized(*args):
      hash_value = hash_func(args)
      if hash_value not in mem:
        # Note args are wrapped as a tuple not list, so it is hashable
        mem[hash_value] = f(*args)
      return mem[hash_value]
    return memorized
  return memoize_wrap

def elapsedtime(f):
  def timed(*args, **kwargs):
    import time
    start = time.time()
    f(*args, **kwargs)
    end = time.time()
    print "running time (in secs): " + str(end - start)
  return timed
