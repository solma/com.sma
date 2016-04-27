TARGETS=endian

float: endian.cc
	clang++ -std=c++11 -stdlib=libc++ -o bin/endian endian.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

