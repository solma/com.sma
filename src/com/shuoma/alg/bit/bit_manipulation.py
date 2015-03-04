def binary(a):
    """
    3.4 convert a decimal float number (passed as a string) to the binary representation
    """
    if '.' not in a:
        print bin(int(a))
    else:
        li = a.split('.')
        integer = li[0]
        fraction = li[1]
        if not fraction:
            print bin(int(integer))
        else:
            remainder = float("." + fraction)
            i = -1
            mantissa = "."
            while remainder:
                if remainder < .00000000001:
                    print "ERROR"
                    return
                while remainder < 2 ** i:
                    i -= 1
                    mantissa += "0"
                remainder -= 2 ** i
                mantissa += "1"
            print bin(int(integer)) + mantissa


def bit_swap_required(a, b):
    """
    3.1 determine the number of bits to convert integer A to integer B
    """
    return _count_1s(a ^ b)


def max_on_bit(a, b):
    """
    3.3 find the bigger of two numbers without using any comparison operator or if-else
    """
    # key is the most significant bit of an integer is the sign bit
    dif = a - b
    return a - (dif >> 31 & 1) * dif


def next_same_1s(a):
    """
    3.7 Given an integer, print the next smallest and next largest number that have the same number
    of 1 bits in their binary representation
    """


def reverse_bin_string(n):
    """
    machine is 64 bit
    """
    print bin(n)
    n = ((n & 0x5555555555555555) << 1) | ((n & 0xAAAAAAAAAAAAAAAA) >> 1)
    print bin(n)
    n = ((n & 0x3333333333333333) << 2) | ((n & 0xCCCCCCCCCCCCCCCC) >> 2)
    print bin(n)
    n = ((n & 0x0F0F0F0F0F0F0F0F) << 4) | ((n & 0xF0F0F0F0F0F0F0F0) >> 4)
    print bin(n)
    n = ((n & 0x00FF00FF00FF00FF) << 8) | ((n & 0xFF00FF00FF00FF00) >> 8)
    print bin(n)
    n = ((n & 0x0000FFFF0000FFFF) << 16) | ((n & 0xFFFF0000FFFF0000) >> 16)
    print bin(n)
    n = (n << 32) | (n >> 32)
    print bin(n)


def swap_odd_even(a):
    """
    3.2 swap odd and even bits in integer, what is the minimum number of instructions required
    """
    return


def swap(a, b):
    """
    3.6 swap two numbers
    """
    a = a ^ b
    b = a ^ b
    a = a ^ b
    return a, b

##################################
# helper functions
##################################


def _count_1s(a):
    """
    count the number of 1's in the binary representation of a number
    """
    a = abs(a)
    count = 0
    while a:
        count += a & 1
        a >>= 1
    return count


reverse_bin_string(0xF0F0F0F0)
