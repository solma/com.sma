def count_no_of_2(n):
	""" 
	count the number of 2's between 0 and n
	"""
	1~9: 1
	1~99: 1+9*(1)+10
	1~999:  20+9*(9*1+10)+100
	1000~9999: 9*(9*20+100)+1000