"""
You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and
ask your friend to guess it, each time your friend guesses a number, you give a hint, the hint tells
your friend how many digits are in the correct positions (called "bulls") and how many digits are
in the wrong positions (called "cows"), your friend will use those hints to find out the secret number.

For example:
Secret number:  1807
Friend's guess: 7810

Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls or Pigs and Bulls or Bulls and Cleots)
is an old code-breaking mind or paper and pencil game for two or more players, predating the similar
commercially marketed board game Mastermind. The numerical version of the game is usually played with
4 digits, but can also be played with 3 or any other number of digits."

Write a function to return a hint according to the secret number and friend's guess,
use A to indicate the bulls and B to indicate the cows, in the above example, your function should
return 1A3B.

You may assume that the secret number and your friend's guess only contain digits, and
their lengths are always equal.
"""

from alg.label import Label
from collections import Counter

Label(Label.String, Label.Array, Label.Hash, Label.LeetCode)

class BullsAndCows:
  def getHint(self, secret, guess):
    bull_cnt = 0
    dict_counter = Counter()
    cows_counter = Counter()
    for i in range(len(secret)):
      if guess[i] == secret[i]:
        bull_cnt += 1
      else:
        dict_counter += Counter([secret[i]])
        cows_counter += Counter([guess[i]])

    cow_cnt = 0
    for candidate in cows_counter.keys():
      if candidate not in dict_counter.keys():
        continue
      cow_cnt += min(dict_counter[candidate], cows_counter[candidate])
    return '%dA%dB' % (bull_cnt, cow_cnt)

from unittest import TestCase
class TestBullsAndCows(TestCase):
  def test_getHint(self):
    ins = BullsAndCows()
    cases = {
      ('11', '10'): '1A0B',
      ('2200', '2022'): '1A2B',
      ('1234', '0111'): '0A1B'
    }
    for case, ans in cases.items():
      self.assertEqual(ans, ins.getHint(case[0], case[1]))

TestBullsAndCows()
