"""
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
"""

from alg.label import Label

Label(Label.Arithmetic, Label.Greedy, Label.LeetCode)


class IntegerToEnglishWords(object):
  def numberToWords(self, num):
    def threeDigitsToWords(n):
      assert 0 < n < 1000
      single_digit = ['One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine']
      teens = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen',
               'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen']
      ties = ['Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety']
      hundreds, tens, digits = n / 100, (n % 100) / 10, n % 10
      word = ''
      if hundreds > 0:
        word += single_digit[hundreds - 1] + ' Hundred'
      if tens > 0:
        if len(word) > 0:
          word += ' '
        if tens > 1:
          word += ' '.join([ties[tens - 2], single_digit[digits - 1] if digits >= 1 else ''])
        else:
          word += teens[digits]
      else:
        if len(word) > 0:
          word += ' '
        word += single_digit[digits - 1] if digits >= 1 else ''
      return word

    if num == 0:
      return 'Zero'
    units = ['', 'Thousand', 'Million', 'Billion']
    base, i = 1000, 0
    word = ''
    while num > 0:
      three_digits = num % base
      if three_digits > 0:
        word = threeDigitsToWords(three_digits) + ' ' + units[i] + ' ' + word
      i += 1
      num /= base
    return ' '.join(word.split()).rstrip()
