"""
Design a data structure that supports all following operations in average O(1)
time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements.
Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 1 is the only number in the set, getRandom always return 1.
randomSet.getRandom();
"""

from alg.label import Label
Label(Label.Array, Label.Hash, Label.ConstantTime, Label.LeetCode)

class RandomizedSet(object):

  def __init__(self):
    self._data_map = {}
    self._data_list = []

  def insert(self, val):
    if val in self._data_map:
      return False
    self._data_map[val] = len(self._data_list)
    self._data_list.append(val)
    return True

  def remove(self, val):
    if val not in self._data_map:
      return False
    last = self._data_list[-1]
    idx = self._data_map[val]
    self._data_list[idx] = last
    self._data_list.pop()
    self._data_map[last] = idx
    del self._data_map[val]
    return True

  def getRandom(self):
    import random as rand
    return self._data_list[rand.randint(0, len(self._data_list) - 1)]

obj = RandomizedSet()
obj.insert(1)
obj.insert(2)
obj.insert(3)
print obj._data_map
print obj.remove(1)
print obj._data_map
print obj.getRandom()