def permutation_recursion(input, s, li):
  if len(s) == len(input):
    li.append(s)
  for i in range(len(input)):
    if input[i] not in s:
      s += input[i]
      permutation_recursion(input, s, li)
      s = s[0:-1]


def combination_recursion(input, s, idx, li):
  for i in range(idx, len(input)):
    s += input[i]
    li.append(s)
    print
    s, idx
    combination_recursion(input, s, i + 1, li)
    s = s[0:-1]


def permutation_iteration(input):
  level = [input[0]]
  for i in range(1, len(input)):
    nList = []
    for item in level:
      nList.append(item + input[i])
      for j in range(len(item)):
        nList.append(item[0:j] + input[i] + item[j:])
    level = nList
  return nList


def combination_iteration(input):
  level = ['']
  for i in range(len(input)):
    nList = []
    for item in level:
      nList.append(item + input[i])
    level += nList
  return level[1:]


def permutation_generator(input, s):
  """
  more pythnic syntax using generator
  """
  if len(s) == len(input): yield s
  for i in input:
    if i in s: continue
    s = s + i
    for ele in permutation_generator(input, s): yield ele
    s = s[:-1]


def combination_generator(input, s, idx):
  """
  more pythnic syntax using generator
  """
  for i in range(idx, len(input)):
    s = s + input[i]
    yield s
    for ele in combination_generator(input, s, i + 1): yield ele
    s = s[:-1]


def xcombination(seq, length):
  if not length:
    yield []
  else:
    for i in range(len(seq)):
      for result in xcombination(seq[i + 1:], length - 1):
        yield [seq[i]] + result


# print list(permutation_generator("abc",""))
# print list(combination_generator("abc","",0))
allPermutations = permutation_generator("abc", "")
print
allPermutations.next()
print
allPermutations.next()

allCombinations = combination_generator("abc", "", 0)
print
allCombinations.next()
print
allCombinations.next()

c_5_3 = xcombination("abcde", 3)
print
list(c_5_3)


# res=[]
# permutation_recursion('abc', '', res)
# combination_recursion('abc', '', 0, res)
# res=permutation_iteration('12345')
# print res
# print combination_iteration('abc')
