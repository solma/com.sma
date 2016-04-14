TARGETS=function-bind lambda-function

function-bind: function-bind.cc
	clang++ -std=c++11 -stdlib=libc++ -o bin/function-bind function-bind.cc

lambda-function: lambda-function.cc
	clang++ -std=c++11 -stdlib=libc++ -o bin/lambda-function lambda-function.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

