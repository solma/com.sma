class MyClass(object):
  def __init__(self, name):
    self.name = name

  def copy(self):
    """
    :param ins: an instance of MyClass
    :return:
    """
    cls = self.__class__
    return cls(self.name + " copy")


if __name__ == '__main__':
  ins = MyClass('first object')
  cpy = ins.copy()
  print(cpy.name)
