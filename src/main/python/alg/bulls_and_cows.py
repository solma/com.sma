"""
You are playing the following Bulls and Cows game with your friend: You write a 4-digit secret number and ask your
friend to guess it, each time your friend guesses a number, you give a hint, the hint tells your friend how many digits
are in the correct positions (called "bulls") and how many digits are in the wrong positions (called "cows"),
your friend will use those hints to find out the secret number.

For example:
Secret number:  1807
Friend's guess: 7810

Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
According to Wikipedia: "Bulls and Cows (also known as Cows and Bulls or Pigs and Bulls or Bulls and Cleots) is an old
code-breaking mind or paper and pencil game for two or more players, predating the similar commercially marketed board
game Mastermind. The numerical version of the game is usually played with 4 digits, but can also be played with 3 or
any other number of digits."

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B
to indicate the cows, in the above example, your function should return 1A3B.

You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
"""

from src.main.python.alg.label import Label

Label(Label.String, Label.Array, Label.Hash, Label.LeetCode)

class BullsAndCows:
  def getHint(self, secret, guess):

    def one_pass(secret, guess):
      # table records the appearance of a digit
      # digit from secret will increase the counter
      # digit from guess will decrease the counter
      count = [0] * 10
      bull_cnt = 0
      cow_cnt = 0

      for i in range(len(secret)):
        a = ord(secret[i]) - ord('0')
        b = ord(guess[i]) - ord('0')
        if a == b:
          bull_cnt += 1
        else:
          # if prev part of guess has curr digit in secret
          # then we found a pair that has same digit with different position
          if count[a] < 0:
            cow_cnt += 1
          # if prev part of secret has curr digit in guess
          # then we found a pair that has same digit with different position
          if count[b] > 0:
            cow_cnt += 1

          count[a] += 1
          count[b] -= 1
      return '%dA%dB' % (bull_cnt, cow_cnt)

    def two_pass(secret, guess):
      from collections import Counter
      bull_cnt = 0
      #dict_counter = MapCounter()
      dict_counter = Counter()
      cows_candidates = []
      for i in range(len(secret)):
        if guess[i] == secret[i]:
          bull_cnt += 1
        else:
          #dict_counter.increment(secret[i], 1)
          dict_counter += Counter([secret[i]])
          cows_candidates += [guess[i]]

      cow_cnt = 0
      for candidate in cows_candidates:
        if candidate in dict_counter.keys():
          cow_cnt += 1
          #dict_counter.increment(candidate, -1)
          dict_counter -= Counter([candidate])
          if dict_counter[candidate] == 0:
            del dict_counter[candidate]
      return '%dA%dB' % (bull_cnt, cow_cnt)

    # return one_pass(secret, guess)
    return two_pass(secret, guess)
