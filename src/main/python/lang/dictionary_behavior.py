def get_undefined_key(dic):
  try:
    return dic['undefined']
  except KeyError as e:
    print(e)  # , str(e))


def replace_all_unformatted_values(dic):
  cnt = 1
  for key in dic:
    if '%(placeholder)s' in dic[key]:
      dic[key] %= {'placeholder': cnt}
      cnt += 1
  return dic


if __name__ == "__main__":
  get_undefined_key({})

  print(replace_all_unformatted_values({
    'key1': 'this is %(placeholder)sth key with placeholder',
    'key2': 'this is %(placeholder)sth key with placeholder',
    'key3': 'this is first key without placeholder'
  }))
