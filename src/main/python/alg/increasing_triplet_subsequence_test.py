from unittest import TestCase
from increasing_triplet_subsequence import IncreasingTripletSubsequence
from utils.random_helper import gen_rand_array

class IncreasingTripletSubsequenceTest(TestCase):
  def test_increasingTriplet(self):
    ins = IncreasingTripletSubsequence()
    for n in range(1000):
      li = gen_rand_array(8, 100, True, True)
      res = ins.increasingTriplet(li)
      if res is False:
        assert ins.increasingTriplet_binary(li) is False
      else:
        if not res[0] < res[1] < res[2]:
          print res
          break
