WORD_LENGTH = 32


def get_index(key, n):
    return key / (WORD_LENGTH / n), key % (WORD_LENGTH / n)  # an integer is 32-bit long


class NBitMap:
    """
    An implementation of NBitMap class
    """

    def __init__(self, n, len=100):
        """
        len is the length of the integer array
        n is the size of element in the nbmap, e.g. n=1 creates a bitmap, n=2 creates a 2-bitmap
        """
        self.len = len
        self.n = n
        self.nbmap = [0] * self.len

    def set_value(self, key, state):
        """
        key is to be inserted into the nbmap
        state is the value of the key, e.g. 0/1 for bitmap, 0/1/2/3 for 2-bitmap
        a_idx: index into the nbmap, e_idx: index into the element of the nbmap
        """
        a_idx, e_idx = get_index(key, self.n)
        #print a_idx, e_idx
        if a_idx > self.len:
            print "Set error: Outbound"
            return

        offset = e_idx * self.n
        self.nbmap[a_idx] &= ~((2 ** self.n - 1) << offset)
        self.nbmap[a_idx] |= state << offset

    def get_value(self, key):
        """
        """
        a_idx, e_idx = get_index(key, self.n)
        #print self.nbmap[a_idx]
        return self.nbmap[a_idx] >> (e_idx * self.n) & (2 ** self.n - 1)

    def count_distinct(self):
        cnt = 0
        for i in self.nbmap:
            for e_idx in range(WORD_LENGTH / self.n):
                if i >> (e_idx * self.n) & (2 ** self.n - 1) == 1:
                    cnt += 1
        return cnt

        # test

nbm = NBitMap(8)
key = 10
nbm.set_value(key, 92)
print nbm.get_value(key)



