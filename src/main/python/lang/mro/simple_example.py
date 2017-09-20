def demo_init():

  class X(object):
    def __init__(self, x, *args):
      self.x = x
      super(X, self).__init__(*args)

  class Y(object):
    def __init__(self, y):
      self.y = y

  class Z(X, Y):
    def __init__(self, *args):
      super(Z, self).__init__(*args)

    def echo(self):
      print(Z.__mro__)
      print('x:%d, y:%d' % (self.y, self.x))

  z = Z(ord('x'), ord('y'))
  z.echo()

def demo_super():

  class A(object):
    def foo(self):
      return 'a'

  class BA(A):
    def foo(self):
      return 'b' + super(BA, self).foo()

  class CA(A):
    def foo(self):
      return 'c' + super(CA, self).foo()

  class DB(BA):
    pass

  class EBC(BA,CA):
    pass

  for c in (CA, DB, EBC):
    print('%s: %s: %s' % (c.__name__, c().foo(), c.__mro__))

def demo_print():
  class A(object):
    def __init__(self):
      print('A enter')
      print(self.__class__)
      print('A leave')

  class BA(A):
    def __init__(self):
      print('BA enter')
      super(BA, self).__init__()
      print('BA leave')

  class CA(A):
    def __init__(self):
      print('CA enter')
      super(CA, self).__init__()
      print('CA leave')

  class B(object):
    def __init__(self):
      print('B enter')
      super(B, self).__init__()
      print('B leave')

  class C(object):
    def __init__(self):
      print('C enter')
      super(C, self).__init__()
      print('C leave')

  class DBC(B, C):
    def __init__(self):
      print('DBC enter')
      super(DBC, self).__init__()
      print('DBC leave')

  class DBCA(BA, CA):
    def __init__(self):
      print('DBCA enter')
      super(DBCA, self).__init__()
      print('DBCA leave')

  ins = DBCA()
  print(DBCA.mro())
  DBC()



# demo_init()
# demo_super()
demo_print()
