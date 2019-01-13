def some_func():
  x = 10

def enclosing_scope():
  def enclosed_func():
    x = 1

  class EnclosedClass(object):
    some_func = some_func
    try:
      enclosed_func = enclosed_func
    except NameError as e:
      print('%s'%e)
      print('class cannot access function/attributes defined at the same enclose level')

  return EnclosedClass

import functools

def func_closure():
  funcs = []
  for i in range(3):
    def f1():
      return i
    def f2(i):
      return i
    funcs.append((f1, functools.partial(f2, i)))
  return funcs

if __name__ == '__main__':
  # enclosing_scope()
  for i, (f1, f2) in enumerate(func_closure()):
    print('idx: %d, f1: %d, f2: %d' %(i, f1(), f2()))
