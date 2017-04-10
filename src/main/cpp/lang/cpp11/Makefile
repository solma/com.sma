TARGETS=function-bind lambda-function stdfunction unique-ptr

unique-ptr: unique-ptr.cc
	g++ -std=c++11 -o bin/unique-ptr unique-ptr.cc

stdfunction: stdfunction.cc
	g++ -std=c++11 -o bin/stdfunction stdfunction.cc

lambda-function: lambda-function.cc
	g++ -std=c++11 -o bin/lambda-function lambda-function.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

