import abc


class Visitor(abc.ABC):
  @abc.classmethod
  def visit(visitee):
    pass


class MediaFile(abc.ABC):
  def __init__(self, path):
    self._path = path

  @abc.classmethod
  def something(self):
    pass
