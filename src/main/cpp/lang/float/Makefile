TARGETS=float

float: float.cc
	clang++ -std=c++11 -stdlib=libc++ -o bin/float float.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

