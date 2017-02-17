def my_func(pos1, pos2, keyword1='', keyword2=''):
  print('pos1: ' + str(pos1))
  print('pos2: ' + str(pos2))
  print('keyword1: ' + str(keyword1))
  print('keyword2: ' + str(keyword2))

if __name__ == '__main__':
  from functools import partial
  pf = partial(my_func,
               '1st position param',
               keyword2='2nd keyword param')
  pf('2nd position param')
