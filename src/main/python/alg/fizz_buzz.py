"""
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of
ive output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
"""
from alg.label import *
Label(Label.Hash, Label.LinearSpace, Label.LeetCode)

class FizzBuzz(object):
  def fizzBuzz(self, n):
    def fizz_buzz(x):
      if x % 15 == 0:
        return 'FizzBuzz'
      elif x % 5 == 0:
        return 'Buzz'
      elif x % 3 == 0:
        return 'Fizz'
      else:
        return str(x)
    return [fizz_buzz(x) for x in range(1, n + 1)]
