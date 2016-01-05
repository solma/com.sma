class MapCounter:
  """
  Simple Counter: map a key space to counters
  """

  def __init__(self):
    self.__counter = {}

  def add(self, s):
    for c in s:
      self.increment(c, 1)

  def increment(self, key, value):
    if key not in self.__counter.keys():
      self.__counter[key] = value
    else:
      self.__counter[key] = self.__counter[key] + value
    if self.__counter[key] == 0:
      del self.__counter[key]

  def keys(self):
    return self.__counter.keys()

  def values(self):
    return self.__counter.values()
