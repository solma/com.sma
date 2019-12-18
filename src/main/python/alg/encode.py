# coding: utf-8
"""
# http://blog.csdn.net/Solstice/article/details/653418

假设有这样一种字符串，它们的长度不大于 26 ，而且若一个这样的字符串其长度为 m ，
则这个字符串必定由 a, b, c ... z 中的前 m 个字母构成，同时我们保证每个字母出现且仅出现一次。
比方说某个字符串长度为 5 ，那么它一定是由 a, b, c, d, e 这 5 个字母构成，不会多一个也不会少一个。
嗯嗯，这样一来，一旦长度确定，这个字符串中有哪些字母也就确定了，唯一的区别就是这些字母的前后顺序而已。

现在我们用一个由大写字母 A 和 B 构成的序列来描述这类字符串里各个字母的前后顺序：

如果字母 b 在字母 a 的后面，那么序列的第一个字母就是 A （After），否则序列的第一个字母就是 B （Before）；
如果字母 c 在字母 b 的后面，那么序列的第二个字母就是 A ，否则就是 B；
如果字母 d 在字母 c 的后面，那么 …… 不用多说了吧？直到这个字符串的结束。

这规则甚是简单，不过有个问题就是同一个 AB 序列，可能有多个字符串都与之相符，
比方说序列"ABA"，就有"acdb"、"cadb"等等好几种可能性。说的专业一点，这一个序列实际上对应了一个字符串集合。
那么现在问题来了：给你一个这样的AB 序列，问你究竟有多少个不同的字符串能够与之相符？
或者说这个序列对应的字符串集合有多大？注意，只要求个数，不要求枚举所有的字符串。
"""


def genPositions(a, position):
    doSum = {'A': (lambda x: sum(position[:x])), 'B': (lambda x: sum(position[x:]))}
    return map(doSum[a[-1]], range(len(a) + 1))


def pn(a):
    if len(a) == 0:
        return [1]
    return genPositions(a, pn(a[0:-1]))


def count(a):
    return sum(pn(a))


print(count("ABAAB"))
