from utils.decorators import memoize

def demo1():
  class DecoratorClassWithNoArgs(object):
    def __init__(self, f):
      print("inside myDecorator.__init__()")
      f() # Prove that function definition has completed

    def __call__(self):
      print("inside myDecorator.__call__()")

  @DecoratorClassWithNoArgs
  def a_function():
    print("inside aFunction()")

  print("For no arg decorators, decorator's __init__() is called when declared, and __call__() is called when" \
        " the decorated method is called.")
  a_function()

def demo2():
  class DecoratorWithArguments(object):

    def __init__(self, arg1, arg2, arg3):
      """
      If there are decorator arguments, the function
      to be decorated is not passed to the constructor!
      """
      print("Inside __init__()")
      self.arg1 = arg1
      self.arg2 = arg2
      self.arg3 = arg3

    def __call__(self, f):
      """
      If there are decorator arguments, __call__() is only called
      once, as part of the decoration process! You can only give
      it a single argument, which is the function object.
      """
      print("Inside __call__()")
      def wrapped_f(*args):
        print("Inside wrapped_f()")
        print("Decorator arguments:", self.arg1, self.arg2, self.arg3)
        f(*args)
        print("After f(*args)")
      return wrapped_f

  @DecoratorWithArguments("hello", "world", 42)
  def say_hello(a1, a2, a3, a4):
    print('sayHello arguments:', a1, a2, a3, a4)

  print("After decoration")

  print("Preparing to call sayHello()")
  say_hello("say", "hello", "argument", "list")
  print("after first sayHello() call")
  say_hello("a", "different", "set of", "arguments")
  print("after second sayHello() call")

def demo3():
  # first pass in decorator args then wrap function
  def decorator_function_with_arguments(arg1, arg2, arg3):
    def wrap(f):
      print("Inside wrap()")
      def wrapped_f(*args):
        print("Inside wrapped_f()")
        print("Decorator arguments:", arg1, arg2, arg3)
        f(*args)
        print("After f(*args)")
      return wrapped_f
    return wrap

  @decorator_function_with_arguments("hello", "world", 42)
  def say_hello(a1, a2, a3, a4):
    print('sayHello arguments:', a1, a2, a3, a4)

  print("Preparing to call sayHello()")
  say_hello("say", "hello", "argument", "list")
  print("after first sayHello() call")
  say_hello("a", "different", "set of", "arguments")
  print("after second sayHello() call")

def demo4():
  @memoize
  def fib(n):
    return n if n < 2 else fib(n - 1) + fib(n - 2)
  print(fib(10))

def demo5():
  @memoize(hash_func=lambda x, y: (x+9, y+9))
  def cum(x, y):
    if x < 0 or y < 0:
      return 0
    return 1 if x == y == 0 else (cum(x - 1, y) + cum(x, y - 1))
  print(cum(2, 2))

if __name__ == '__main__':
  demo1()
  demo2()
  demo3()
  demo4()
  demo5()



