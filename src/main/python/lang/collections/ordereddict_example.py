from collections import OrderedDict

def demo():
  dd = OrderedDict()
  dd['a'] = 3
  dd['b'] = 1
  dd['c'] = 2
  assert (dd.items()[0] == ('a', 3))
  del dd['a']
  assert (dd.items()[0] == ('b', 1))

if __name__ == '__main__':
  demo()
