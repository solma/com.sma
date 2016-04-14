#include <functional>
#include <iostream>
#include <vector>

namespace { // unamed namespace
 class BindClassMemberMethod {
   public:
     void run() const {
      partialFooMinus();
     }

     void fooMinus(const std::string & msg, int a, int b) const {
       std::cout << msg << ": " << a - b << std::endl;
     }

     void partialFooMinus() const {
       // bind a class member function within the class body
       // 2nd paramter is an instance of the class
       auto bind_member = std::bind(&BindClassMemberMethod::fooMinus, this, "inside class", std::placeholders::_1, 0);
       bind_member(2);
     }
 };

 void dummyMethod(const std::string & msg) {
   std::cout << msg << std::endl;
 }
} // namespace


int main(int argc, char** argv)
{
  BindClassMemberMethod foo;
  foo.run();

  // bind a class member function outside the class body
  // 2nd paramter is an instance of the class
  auto bind_member = std::bind(&BindClassMemberMethod::fooMinus, &foo, "outside class, in the main()", std::placeholders::_1, 0);
  bind_member(2);
  auto bind_dummy = std::bind(dummyMethod, std::placeholders::_1);
  bind_dummy("no instance needed to bind a namespace function.");
}

