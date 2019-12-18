from unittest import TestCase
from unittest import mock

from lang.mock_example import MyClass


class MyClassTest(TestCase):
  @mock.patch.object(MyClass, 'callee', autospec=True)
  def testAutoSpec1(self, mock_callee):

    def test_call_count(count):
      self.assertEqual(mock_callee.call_count, count)

    ins = MyClass()
    #with mock.patch('mock_example.MyClass') as MockClass:
      # class_instance = MockClass.return_value
      # class_instance.callee.return_value='mock via decorator.'
      # ins = MyClass()
    mock_callee.return_value='mock via decorator.'
    self.assertEqual('mock via decorator.', ins.caller('callee', 1, 2))
    test_call_count(1)

    mock_callee.return_value='mock via decorator again.'
    self.assertEqual('mock via decorator again.', ins.caller('callee', 1, 2))
    test_call_count(2)
    try:
      # call it with only one parameter
      self.assertEqual('mock via decorator.', ins.caller('callee', 1))
    except TypeError:
      print('there will be no exception without autospec=True, '
            'and thus the test passes even if the API has changed.')

  def testAutoSpec2(self):
    MyClass.callee = mock.create_autospec(MyClass.callee, return_value='mock via create_autospec.')
    self.assertEqual('mock via create_autospec.', MyClass().caller('callee', 1, 2))
