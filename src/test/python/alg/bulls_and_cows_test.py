from unittest import TestCase

from src.main.python.alg.bulls_and_cows import Solution


class TestSolution(TestCase):
    def test_getHint(self):
        ins = Solution()
        cases = {
            ('11', '10'): '1A0B',
            ('11', '01'): '1A0B',
            ('1234', '0111'): '0A1B'
        }
        for case, ans in cases.iteritems():
            self.assertEqual(ans, ins.getHint(case[0], case[1]))
