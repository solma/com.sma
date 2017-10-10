TARGETS=function_pointer copy_elision return_value

copy_elision: copy_elision.cc dummy_class.h
	g++ -std=c++14 -o bin/copy_elision copy_elision.cc

function_pointer: function_pointer.cc
	g++ -std=c++14 -o bin/function_pointer function_pointer.cc

return_value: return_value.cc dummy_class.h
	g++ -std=c++14 -o bin/return_value return_value.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

