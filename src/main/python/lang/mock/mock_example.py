from unittest.mock import patch

class MyClass(object):
  def callee(self, a, b):
    return 'Inside Callee'

  def caller(self, callee_name, *args):
    return getattr(self, callee_name)(*args)

if __name__ == '__main__':
  with patch('__main__.MyClass') as MockClass:
    cls_instance = MockClass.return_value
    cls_instance.callee.return_value = 'foo'
    ins = MyClass()
    assert ins is cls_instance
    assert ins.callee(1, 2) == 'foo'
