import collections

BaseTuple = collections.namedtuple('BaseTuple', ['x', 'y'])

ExtraTuple = collections.namedtuple('ExtraTuple', ['a', 'b'])

_DUMMY_BASE_TUPLE = BaseTuple(x=25, y=26)


class CompositeTuple(object):
  """CompositeTuple is composed of two namedtuple classes."""

  def __init__(self, base_tuple=None, extra_tuple=None, **kwargs):
    """Constructs an `CompositeTuple` instance.

    Args:
      base_tuple: (BaseTuple)
      extra_tuple: (ExtraTuple)
      **kwargs: kwargs passed in to build base_tuple or extra_fields

    Raises:
      ValueError: if base_tuple and extra_fields have common fields or
        keywords and base_tuple are specified at the same time.
    """
    if base_tuple and kwargs:
      raise ValueError(
        'Cannot have kwargs and base_tuple specified at the same time.')
    if base_tuple:
      self._base_tuple = base_tuple
      self._extra_tuple = extra_tuple
    else:
      # initialize from kwargs
      base_tuple_fields, extra_tuple = self._kwargs_to_dicts(**kwargs)
      self._base_tuple = BaseTuple(**base_tuple_fields)
      self._extra_tuple = ExtraTuple(**extra_tuple)

    self._fields = set(self._base_tuple._fields) | set(self._extra_tuple._fields)

    common_fields = (
        set(self._base_tuple._fields) & set(self._extra_tuple._fields))
    if common_fields:
      raise ValueError(
        'ExtraTuple and BaseTuple have following common fields %s,'
        'this will lead to field resolution failure.' %
        ','.join(common_fields))

  def __getattr__(self, field):
    if field in _DUMMY_BASE_TUPLE._fields:
      return getattr(self._base_tuple, field)
    else:
      return getattr(self._extra_tuple, field)

  def _kwargs_to_dicts(self, **kwargs):
    base_tuple_fields, extra_tuple_fields = {}, {}
    for key, value in kwargs.items():
      if key in _DUMMY_BASE_TUPLE._fields:
        base_tuple_fields[key] = value
      else:
        extra_tuple_fields[key] = value
    return base_tuple_fields, extra_tuple_fields

  def _replace(self, **kwargs):
    base_tuple, extra_tuple = self._kwargs_to_dicts(**kwargs)
    self._base_tuple = self._base_tuple._replace(**base_tuple)
    self._extra_tuple = self._extra_tuple._replace(**extra_tuple)
    return self

  def _asdict(self):
    ret = self._base_tuple._asdict()
    ret.update(self._extra_tuple._asdict())
    return ret


composite_tuple = CompositeTuple(_DUMMY_BASE_TUPLE, extra_tuple=ExtraTuple(a=1, b=2))
print(composite_tuple._asdict())
print(composite_tuple._fields)
print(composite_tuple.x)
print(composite_tuple.a)
composite_tuple._replace(x=26)
print(composite_tuple.x)
