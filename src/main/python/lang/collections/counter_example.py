from collections import Counter

def demo():

  def pprint(msg, ctr):
    print msg + " : " + str(ctr)

  def RemoveZeroCnt(ctr):
    zero_cnts = [ele for ele in set(ctr) if ctr[ele] == 0]
    for ele in zero_cnts:
      del ctr[ele]
    return ctr

  def ConvertToPercentage(ctr):
    tot_sum = sum(ctr)
    freq = dict(ctr)
    for k in freq.keys():
      freq[k] /= tot_sum * 1.0
    return freq

  li = range(1, 10, 2) + range(2, 10, 3) + range(3, 10, 4)
  ctr = Counter(li)
  pprint("Initially", ctr)
  pprint("All keys", list(ctr))
  pprint("All elements", list(ctr.elements()))
  ctr.subtract([1, 2, 3])
  pprint("After subtraction", ctr)
  pprint("After removing zero counts", RemoveZeroCnt(ctr))
  ctr.update([1, 2])
  pprint("After addition", ctr)
  pprint("Converted to frequency", ConvertToPercentage(ctr))

if __name__ == '__main__':
  demo()
