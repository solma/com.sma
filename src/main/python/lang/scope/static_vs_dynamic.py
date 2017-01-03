def main(x):

  def func1():
    a = 1
    bar()

  def func2():
    a = 2
    bar()

  def bar():
    print(a)

  a = 0
  func1() if x > 0 else func2()

print('static scope always print 0 and dynamic scope will print 1 or 2 based on the value of x')
main(1)
