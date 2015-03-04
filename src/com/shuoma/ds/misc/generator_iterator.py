def fib_generator():
	a=0
	b=1
	yield a
	while(True):
		yield b
		a, b=b, a+b

		
class FibIterator:
	def __init__(self, iteration_step):
		self.m=iteration_step
		
	def __iter__(self):
		self.a=0
		self.b=1
		self.count=2
		return self
		
	def __next__(self):
		if self.count > self.m: 
			raise StopIteration
		self.count+=1
		self.a, self.b=self.b, self.a+self.b
		return self.a

class Fib:                                        
    def __init__(self, max):                      
        self.max = max
        if max<0:
			raise Exception

    def __iter__(self):                           
        self.a = 0
        self.b = 1
        return self

    def __next__(self):                           
        fib = self.a
        if fib > self.max:
            raise StopIteration                   
        self.a, self.b = self.b, self.a + self.b
        return fib    		
			

if __name__=="__main__":
	fib_gen=fib_generator()
	#for i in range(10):
	#	print fib_gen.next()
	
	for i in FibIterator(10):
		print i