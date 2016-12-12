from functools import partial
from multiprocessing import Manager
import threading

class Name(object):
  def __init__(self, first_name, last_name):
    self._first_name = first_name
    self._last_name = last_name

  @property
  def first_name(self):
    return self._first_name

  @first_name.setter
  def first_name(self, value):
    self._first_name = value

  @property
  def last_name(self):
    return self._last_name

  @last_name.setter
  def last_name(self, value):
    self._last_name = value

  def __str__(self):
    return '(%s, %s)' % (self._first_name, self._last_name)

class SynchronizedListImplementation(object):
  MULTIPROCESSING_MANAGER = 1
  THREAD_LOCK = 2

class Family(object):

  def __init__(self, last_name,
               synchronized_approach=SynchronizedListImplementation.THREAD_LOCK):
    print 'Create a new family object'
    self._last_name = last_name
    self.synchronized_approach = synchronized_approach
    if self.synchronized_approach == SynchronizedListImplementation.MULTIPROCESSING_MANAGER:
      self._members = Manager().list()
    else:
      self._members = []
      self._lock = threading.Lock()
    self._cnt = 0

  def Add(self, first_name):
    # if self.synchronized_approach == SynchronizedListImplementation.THREAD_LOCK:
    #   self._lock.acquire()
    name = Name(first_name, self._last_name)
    if name not in self._members:
      self._members.append(name)
    # if self.synchronized_approach == SynchronizedListImplementation.THREAD_LOCK:
    #   self._lock.release()

  @property
  def last_name(self):
    return self._last_name

  @last_name.setter
  def last_name(self, value):
    self._last_name = value

  def __str__(self):
    return '%s Family:\n%s' % (
      self._last_name, '\n'.join([str(name) for name in self._members]))

def PrintFullName(first_name, last_name):
  return str(Name(first_name, last_name))

def CreateFamily(last_name):
  return partial(PrintFullName, last_name=last_name)

if __name__ == '__main__':
  ma_family = CreateFamily('Ma')
  print ma_family('Lexi')
