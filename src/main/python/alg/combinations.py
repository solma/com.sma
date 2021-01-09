from alg.label import *
Label(Label.Recursion)

def permutation_recursion(input, s, li):
  if len(s) == len(input):
    li.append(s)
  for i in range(len(input)):
    if input[i] not in s:
      s += input[i]
      permutation_recursion(input, s, li)
      s = s[:-1]


def combination_recursion(input, s, idx, li):
  for i in range(idx, len(input)):
    s += input[i]
    li.append(s)
    print(s, idx)
    combination_recursion(input, s, i + 1, li)
    s = s[:-1]


def permutation_generator(input, s):
  if len(s) == len(input): yield s
  for i in input:
    if i in s: continue
    s = s + i
    for ele in permutation_generator(input, s): yield ele
    s = s[:-1]


def combination_generator(input, s, idx):
  for i in range(idx, len(input)):
    s = s + input[i]
    yield s
    for ele in combination_generator(input, s, i + 1): yield ele
    s = s[:-1]


def xcombination_generator(seq, length):
  if not length:
    yield []
  else:
    for i in range(len(seq)):
      for result in xcombination_generator(seq[i + 1:], length - 1):
        yield [seq[i]] + result


def permutation_iteration(input):
  ret = ['']
  for i in range(len(input)):
    nList = []
    for item in ret:
      for j in range(len(item) + 1):
        nList.append(item[0:j] + input[i] + item[j:])
    ret = nList
  return ret


def combination_iteration(input):
  ret = ['']
  for i in range(len(input)):
    nList = []
    for item in ret:
      nList.append(item + input[i])
    ret += nList
  return ret[1:]


res=[]
permutation_recursion('abcd', '', res)
assert(set(permutation_iteration('abcd')) == set(res))

allPermutations = permutation_generator('abc', '')
print(next(allPermutations))
print(next(allPermutations))

allCombinations = combination_generator('abc', '', 0)
print(next(allCombinations))
print(next(allCombinations))

c_5_3 = xcombination_generator('abcde', 3)
print(list(c_5_3))
