TARGETS=lambda-function stdfunction unique-ptr string-demo move_demo

unique-ptr: unique-ptr.cc
	g++ -std=c++14 -o bin/unique-ptr unique-ptr.cc

string-demo: string-demo.cc
	g++ -std=c++14 -o bin/string-demo string-demo.cc

move_demo: move_demo.cc
	g++ -std=c++14 -o bin/move_demo move_demo.cc

stdfunction: stdfunction.cc
	g++ -std=c++14 -o bin/stdfunction stdfunction.cc

lambda-function: lambda-function.cc
	g++ -std=c++14 -o bin/lambda-function lambda-function.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

