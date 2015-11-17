def get_undefined_key(dict):
  try:
    return dict['undefined']
  except KeyError as e:
    print(e)  # , str(e))


def replace_all_unformatted_values(dict):
  cnt = 1
  for key in dict:
    if '%(placeholder)s' in dict[key]:
      dict[key] %= {'placeholder': cnt}
      cnt += 1
  return dict


if __name__ == "__main__":
  get_undefined_key({})

  print(replace_all_unformatted_values({
    'key1': 'this is %(placeholder)sth key with placeholder',
    'key2': 'this is %(placeholder)sth key with placeholder',
    'key3': 'this is first key without placeholder'
  }))
