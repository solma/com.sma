def demo1():
  class DecoratorClassWithNoArgs(object):
    def __init__(self, f):
      print "inside myDecorator.__init__()"
      f() # Prove that function definition has completed

    def __call__(self):
      print "inside myDecorator.__call__()"

  @DecoratorClassWithNoArgs
  def a_function():
    print "inside aFunction()"

  print "For no arg decorators, decorator's __init__() is called when declared, and __call__() is called when" \
        " the decorated method is called."
  a_function()

def demo2():
  # first pass in decorator args then wrap function
  def decoratorFunctionWithArguments(arg1, arg2, arg3):
    def wrap(f):
      print "Inside wrap()"
      def wrapped_f(*args):
        print "Inside wrapped_f()"
        print "Decorator arguments:", arg1, arg2, arg3
        f(*args)
        print "After f(*args)"
      return wrapped_f
    return wrap

  @decoratorFunctionWithArguments("hello", "world", 42)
  def sayHello(a1, a2, a3, a4):
    print 'sayHello arguments:', a1, a2, a3, a4

  print "Preparing to call sayHello()"
  sayHello("say", "hello", "argument", "list")
  print "after first sayHello() call"
  sayHello("a", "different", "set of", "arguments")
  print "after second sayHello() call"

def demo3():
  def memoize(f):
    def memorized(*args):
      if args not in cached_result:
        cached_result[args] = f(*args)
      return cached_result[args]

    cached_result = {}
    return memorized

  @memoize
  def fib(n):
    return n if n < 2 else fib(n - 1) + fib(n - 2)

  print fib(10)

if __name__ == '__main__':
  # demo1()
  # demo2()
  demo3()


