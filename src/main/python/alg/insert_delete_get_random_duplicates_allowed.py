"""
Design a data structure that supports all following operations in average O(1)
time.

insert(val): Inserts an item val to the collection if not already present.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
Each element must have the same probability of being returned.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
"""

from alg.label import *
Label(Label.Array, Label.Hash, Label.ConstantTime, Label.LeetCode)

class RandomizedCollection(object):

  def __init__(self):
    self._data_map = {} # val-cnt to idx
    self._data_cnt = {} # val to cnt
    self._data_list = []
    self._key_delimiter = ':'

  def insert(self, val):
    if val not in self._data_cnt:
      self._data_cnt[val] = 0
    self._data_cnt[val] += 1

    key = self._getKey(val)
    self._data_map[key] = len(self._data_list)
    self._data_list.append(key)
    return True

  def remove(self, val):
    if val not in self._data_cnt:
      return False

    key = self._getKey(val)
    idx = self._data_map[key]
    key_last_element = self._data_list[-1]
    self._data_list[idx] = key_last_element
    self._data_map[key_last_element] = idx

    self._data_list.pop()
    del self._data_map[key]
    self._data_cnt[val] -= 1
    if self._data_cnt[val] == 0:
      del self._data_cnt[val]
    return True

  def getRandom(self):
    import random as rand
    return int(self._data_list[rand.randint(0, len(self._data_list) - 1)].split(
      self._key_delimiter)[0])

  def _getKey(self, val):
    return str(val) + self._key_delimiter + str(self._data_cnt[val])

obj = RandomizedCollection()
obj.insert(-1)
obj.remove(-2)
obj.insert(-2)
print obj._data_map
print obj.getRandom()
print obj._data_map
print obj.remove(-1)
print obj._data_map
obj.insert(-2)
