TARGETS=function-bind lambda-function

function-bind: function-bind.cc
	g++ -std=c++11 -o bin/function-bind function-bind.cc

lambda-function: lambda-function.cc
	g++ -std=c++11 -o bin/lambda-function lambda-function.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

