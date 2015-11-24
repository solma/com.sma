class Grandpa(object):
  def __init__(self, **kwargs):
    self.grandpa = kwargs['grandpa']

class Grandma(object):
  def __init__(self, **kwargs):
    self.grandma = kwargs['grandma']

class Father(Grandpa, Grandma):
  def __init__(self, **kwargs):
    Grandpa.__init__(self, **kwargs)
    Grandma.__init__(self, **kwargs)
    self.father = kwargs['father']

class Child(Father):
  def __init__(self, **kwargs):
    Father.__init__(self, **kwargs)
    self.child = kwargs['child']

if __name__ == '__main__':
  c = Child(child='c', father='f', grandpa='p', grandma='m')
  print(c.child, c.father, c.grandpa, c.grandma)
