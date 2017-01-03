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

if __name__ == '__main__':
  enclosing_scope()
