from unittest import TestCase
from src.main.python.alg.increasing_triplet_subsequence import IncreasingTripletSubsequence
from src.main.python.utils.random_helper import gen_rand_array

class IncreasingTripletSubsequenceTest(TestCase):
  def test_increasingTriplet(self):
    ins = IncreasingTripletSubsequence()
    for n in range(100):
      li = gen_rand_array(8, 100, True, True)
      res = ins.increasingTriplet(li)
      if res is False:
        assert ins.increasingTriplet_binary(li) is False
      else:
        assert res[0] < res[1] < res[2]
        assert li[res[0]] < li[res[1]] < li[res[2]]
