"""
There is a list of sorted integers from 1 to n.
Starting from left to right, remove the first number and every other number afterward until
you reach the end of the list.

Repeat the previous step again, but this time from right to left,
remove the right most number and every other number from the remaining numbers.

We keep repeating the steps again, alternating left to right and right to left,
until a single number remains.

Find the last number that remains starting with a list of length n.

Example:

Input:
n = 9,
1 2 3 4 5 6 7 8 9
2 4 6 8
2 6
6

Output:
6

"""


class ElimationGame(object):
  def lastRemaining(self, n):
    iteration = 0  # record the iteration
    point = 1  # point to the first element in the list
    interval = 1  # record the interval between elements
    while n > 1:
      # if we eliminate from the left or the length of list is even,
      # the first element will move forward one place and the interval will double
      if n % 2 == 1 or iteration % 2 == 0:
        point += interval
        iteration += 1
        interval *= 2
        n = n // 2
      # if we eliminate the element from right and the length of list is odd, the first element doesn't change
      else:
        iteration += 1
        interval *= 2
        n = n // 2
    return point

  def lastRemaining1(self, n):
    ret = range(1, n + 1)
    flag = True
    while len(ret) > 1:
      ret = ret[1::2] if flag else ret[-2::-2][::-1]
      flag ^= True
    return ret[0]


ins = ElimationGame()
print(ins.lastRemaining(10))
