TARGETS=template

template: template.cc
	clang++ -std=c++11 -stdlib=libc++ -o bin/template template.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

