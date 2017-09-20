TARGETS=function_pointer copy_ellision return_value

copy_ellision: copy_ellision.cc dummy_class.h
	g++ -std=c++14 -o bin/copy_ellision copy_ellision.cc

function_pointer: function_pointer.cc
	g++ -std=c++14 -o bin/function_pointer function_pointer.cc

return_value: return_value.cc dummy_class.h
	g++ -std=c++14 -o bin/return_value return_value.cc

all: $(TARGETS)

clean:
	rm -f $(TARGETS)

