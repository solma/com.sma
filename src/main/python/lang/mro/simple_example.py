class A(object):
  def __init__(self, x):
    self.x = 3

class B(object):
  def __init__(self, y):
    self.y = 2

class C(B, A):
  def echo(self):
    print('%d:%d' % (self.y, self.x))

c = C(1)
c.echo()
