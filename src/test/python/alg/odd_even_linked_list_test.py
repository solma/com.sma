from unittest import TestCase

from src.main.python.alg.data_structure import *
from src.main.python.alg.odd_even_linked_list import OddEvenLinkedList

class OddEvenLinkedListTest(TestCase):
  def test_oddEvenList(self):
    ins = OddEvenLinkedList()
    cases = [
      (make_list(range(1, 9)), make_list(range(1, 9, 2) + range(2, 9, 2)))
    ]
    for case in cases:
      res = ins.oddEvenList(case[0])
      print list2str(res)
      print list2str(case[1])
      self.assertTrue(is_equal_list(res, case[1]))
