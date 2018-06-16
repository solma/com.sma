from collections import Counter

def demo():

  def my_print(msg, ctr):
    print(msg + " : " + str(ctr))

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

  li = list(range(1, 10, 2)) + list(range(2, 10, 3)) + list(range(3, 10, 4))
  ctr = Counter(li)
  my_print("Initially", ctr)
  my_print("All keys", list(ctr))
  my_print("All elements", list(ctr.elements()))
  ctr.subtract([1, 2, 3])
  my_print("After subtraction", ctr)
  my_print("After removing zero counts", RemoveZeroCnt(ctr))
  ctr.update([1, 2])
  my_print("After addition", ctr)
  my_print("Converted to frequency", ConvertToPercentage(ctr))

if __name__ == '__main__':
  demo()
