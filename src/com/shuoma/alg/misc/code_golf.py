# ##############################
# Number Theory
def gcd(a,b):
	return gcd(b, a%b) if b else a

def lcm(a,b):
	return a*b/gcd(a,b)

def prime(n):
	if n<2: return False
	for i in range(2,  int(round(n**0.5+1))): 
		if not n%i: return False
	return True
	
def factoring(n):
	if prime(n): return [(n, 1)]
	li=[]
	for i in range(2, n):
		if prime(i):
			cnt = 0
			while n>0 and n%i==0: 
				cnt +=1
				n /= i
			if cnt>0: li.append((i,cnt) )
			if n==0: break
	return li
# ##############################




# ###############################
# Sorting
def quick_sort_sc (ls): 
	return quick_sort_sc([y for y in ls[1:] if y < ls[0]]) + [ls[0]] + quick_sort_sc([y for y in ls[1:] if y >= ls[0]]) if ls else [] 
    
def select(li, k, left, right):
    while True:
        pivot_idx=partition(li, left+(right-left)/2, left, right )
        pivot_dist=pivot_idx-left+1
        if pivot_dist==k:
            return
        else:
            if pivot_dist<k:
                left=pivot_idx+1
                k-=pivot_dist
            else:
                right=pivot_idx-1
                   

def quick_sort(li, left, right):
    """
    return top k elements in li
    """
    if left<right:
        pivot_idx=partition(li, (left+right)/2, left, right)
        quick_sort(li,  left, pivot_idx-1)
        quick_sort(li, pivot_idx+1, right) 

def partition(li, pivot_index, left, right):
      pivot_value = li[pivot_index]
      li[pivot_index], li[right]=li[right], li[pivot_index]   # Move pivot to end
      store_index = left
      for i in range(left,  right):  # left <= i < right
          if li[i] < pivot_value:
              li[i], li[store_index] = li[store_index], li[i]
              store_index = store_index + 1
      li[store_index], li[right]= li[right], li[store_index]  # Move pivot to its final place
      return store_index

            

    
    
from operator import itemgetter
def align_number(num, length):
    s=str(num)
    if len(s)<length:
        for i in range(length-len(s)):
            s="0"+s
    return s
    
def radix_sort(arr):
    max_length=len(str(max(arr)))
    string_arr=[align_number(i, max_length) for i in arr]
    cur_bit=[(0,ele) for ele in string_arr]
    
    for i in range(max_length)[::-1]: # for each bit
                
        for j in range(len(string_arr)): #extract the current bit of each element
            cur_bit[j]=(string_arr[j][i], string_arr[j])
        cur_bit=sorted(cur_bit, key=itemgetter(0))
        string_arr=[ele[1]  for ele in cur_bit]
        
        
    return [int(ele) for ele in string_arr]
# ###############################

# #################################
# Searching
from itertools import permutations
def n_queens(n=8):  
	"""
	any permutation with no queens on the same diagonal is a valid solution
	"""
	cols = range(n)
	cnt=0
	for vec in permutations(cols):
		if (n == len(set(vec[i] + i for i in cols)) == len(set(vec[i] - i for i in cols))):  cnt+=1
	return cnt
	
def sudoko(line):
	"""
	solving arbitray sudoko
	"""
	board=[0 if c=='.' else int(c) for c in line]
	print_sudoko(board)
	return
	
def print_sudoko(board):
	for i in range(len(board)):
		if i%3==0:
			if i%9>0: print '|',
			else: print
		if i%27==0 and i: print ''.join(['-']*6+['+']+['-']*7+['+']+['-']*6)
		print board[i],
		
		
	
# #####################################
if __name__=="__main__":
    array=[10, 231,195, 3,133,6275,610,721]
    #print radix_sort(array)
    
    #quick_sort(array, 0, len(array)-1)
    #print array
    
    select(array, 3, 0, len(array)-1)
    print array