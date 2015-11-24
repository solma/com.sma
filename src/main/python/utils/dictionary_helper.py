import ast
import collections
import pprint as pp
import sys


class DictDiffer(object):
  def __init__(self, this, that, path=None):
    self.this, self.that = this, that
    self._this_keys, self._that_keys = set(this.keys()), set(that.keys())
    self.intersect = self._this_keys.intersection(self._that_keys)
    self._path = path

  def added(self):
    added = {}
    for key in self._that_keys - self.intersect:
      added[key] = self.that[key]
    return pp.pformat(added)

  def removed(self):
    removed = {}
    for key in self._this_keys - self.intersect:
      removed[key] = self.this[key]
    return pp.pformat(removed)

  def equal_special(self, o1, o2):
    if str(o1).lower() == 'true' == str(o2).lower():
      return True
    if str(o1).lower() == 'false' == str(o2).lower():
      return True
    try:
      float(o1) == float(o2)
      return True
    except ValueError:
      pass
    return False

  def changed(self):
    changed = {}
    unchanged_keys = self.unchanged()[0]
    for o in self.intersect:
      if o not in unchanged_keys:
        if isinstance(self.this[o], dict) and isinstance(self.that[o], dict):
          changed[o] = 'to-be-expanded'
        else:
          changed[o] = str(self.this[o]) + ' --> ' + str(self.that[o])
    return pp.pformat(changed)

  def unchanged(self):
    unchanged = {}
    for o in self.intersect:
      if self.that[o] == self.this[o] or self.equal_special(self.this[o], self.that[o]):
        unchanged[o] = self.that[o]
    return unchanged.keys(), pp.pformat(unchanged)

  def diff(self):
    unchanged_keys, unchanged_ret = self.unchanged()
    no_diff = len(unchanged_keys) == len(self._this_keys) == len(self._that_keys)
    ret = (self._path if self._path else '') + '\n'
    ret += 'added:\n' + str(self.added()) + '\n' + \
           'removed:\n' + str(self.removed()) + '\n' + \
           'changed:\n' + str(self.changed()) + '\n' + \
           'unchanged:\n' + str(unchanged_ret) + '\n\n'
    for o in self.intersect:
      if self.that[o] != self.this[o] and isinstance(self.this[o], dict) and isinstance(self.that[o], dict):
        ret += DictDiffer(self.that[o], self.this[o], (self._path + '.' if self._path else '') + o).diff()
    return no_diff, ret

  def diff_flatten(self):
    this_flatten = flatten(self.this)
    that_flatten = flatten(self.that)
    return DictDiffer(this_flatten, that_flatten).diff()


def as_dict(key_values):
  """
  :param key_values: e.g.  k1=v1,k2=v2
  :return dict
  """
  dic = {}
  key_values.replace('//s+', '')
  try:
    for key_value in key_values.split(','):
      idx_first_equal = key_value.find('=')
      dic[key_value[:idx_first_equal]] = key_value[idx_first_equal + 1:]
  except:
    print(key_value)
    e = sys.exc_info()[0]
    print(e)
    raise
  return dic


def flatten(dic, parent_key='', sep='.'):
  items = []
  for k, v in dic.items():
    new_key = parent_key + sep + k if parent_key else k
    if isinstance(v, collections.MutableMapping):
      items.extend(flatten(v, new_key, sep=sep).items())
    else:
      items.append((new_key, v))
  return dict(items)


def diff(this_dict_repr,
         that_dict_repr,
         this_dict_factory = ast.literal_eval,
         that_dict_factory = ast.literal_eval,
         print_result = False):
  no_diff, ret = DictDiffer(this_dict_factory(this_dict_repr), that_dict_factory(that_dict_repr)).diff_flatten()
  if print_result and not no_diff:
    print(ret)
  return no_diff
