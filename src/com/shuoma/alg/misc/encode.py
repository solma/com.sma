#http://blog.csdn.net/Solstice/article/details/653418

def genPositions(a, position):
 doSum = { 'A':(lambda x: sum(position[:x])),'B':(lambda x: sum(position[x:])) }
 return map( doSum[a[-1]], range(len(a)+1) )

def pn(a):
 if( len(a) == 0 ): return [1]
 return genPositions(a, pn(a[0:-1]))

def count(a):
 return sum(pn(a))

print count("ABAAB")
