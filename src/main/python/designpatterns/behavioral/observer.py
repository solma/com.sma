from __future__ import annotations
import abc
import random
import typing


class Subject(abc.ABC):
  """
  The Subject interface declares a set of methods for managing subscribers.
  """

  @abc.abstractmethod
  def attach(self, observer: Observer) -> None:
    """
    Attach an observer to the subject.
    """
    pass

  @abc.abstractmethod
  def detach(self, observer: Observer) -> None:
    """
    Detach an observer from the subject.
    """
    pass

  @abc.abstractmethod
  def notify(self) -> None:
    """
    Notify all observers about an event.
    """
    pass


class MediaPlayer(Subject):
  """
  The Subject owns some important state and notifies observers when the state
  changes.
  """

  """
  For the sake of simplicity, the Subject's state, essential to all
  subscribers, is stored in this variable.
  """
  _state: int = None

  """
  List of subscribers. In real life, the list of subscribers can be stored
  more comprehensively (categorized by event type, etc.).
  """
  _observers: typing.List[Observer] = []

  def attach(self, observer: Observer) -> None:
    print("Subject: Attached an observer.")
    self._observers.append(observer)

  def detach(self, observer: Observer) -> None:
    self._observers.remove(observer)

  """
  The subscription management methods.
  """

  def notify(self) -> None:
    """
    Trigger an update in each subscriber.
    """
    print("Subject: Notifying observers...")
    for observer in self._observers:
      observer.update(self)

  def play(self) -> None:
    """
    Usually, the subscription logic is only a fraction of what a Subject can
    really do. Subjects commonly hold some important business logic, that
    triggers a notification method whenever something important is about to
    happen (or after it).
    """
    print("\nSubject: Playing media files.")
    self._state = random.randint(0, 10)

    print(f"Subject: My state has just changed to: {self._state}")
    self.notify()

class Observer(abc.ABC):
  """
  The Observer interface declares the update method, used by subjects.
  """

  @abc.abstractmethod
  def update(self, subject: Subject) -> None:
    """
    Receive update from subject.
    """
    pass

class AviFile(Observer):
  def update(self, subject: Subject) -> None:
    if subject._state < 3:
      print("ConcreteObserverA: Reacted to the event")


class MkvFile(Observer):
  def update(self, subject: Subject) -> None:
    if subject._state == 0 or subject._state >= 2:
      print("ConcreteObserverB: Reacted to the event")

if __name__ == "__main__":
  # The client code.

  mp = MediaPlayer()

  avi_file = AviFile()
  mp.attach(avi_file)

  mkv_file = MkvFile()
  mp.attach(mkv_file)

  mp.play()

  mp.detach(avi_file)

  mp.play()
